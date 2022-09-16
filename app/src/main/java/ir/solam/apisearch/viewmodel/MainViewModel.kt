package ir.solam.apisearch.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import ir.solam.apisearch.firstQuery
import ir.solam.apisearch.ioDispatcher
import ir.solam.apisearch.model.*
import ir.solam.apisearch.queryUrl
import kotlinx.coroutines.launch
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

class MainViewModel(
    private val itemRepo: ItemRepositoryImpl
) : ViewModel() {
    val query: MutableLiveData<String> = MutableLiveData(firstQuery)
    val itemsToShow: MutableLiveData<List<ItemToShow>> = MutableLiveData(listOf())
    private var dbItemsToShow = emptyList<ItemToShow>()

    init {
        viewModelScope.launch(ioDispatcher) {
//            val dbItemsToShow = itemRepo.searchItemTitle(query)
            dbItemsToShow = itemRepo.all()?.map {
                ItemToShow(
                    it.movie_id,
                    it.movie_title,
                    it.movie_title_en,
                    it.pic,
                    it.countries
                )
            } ?: emptyList()
            if (dbItemsToShow.isNotEmpty()) {
                itemsToShow.postValue(dbItemsToShow)
            }
            getAndSaveData(firstQuery)
        }
    }

    suspend fun getAndSaveData(query: String) {
        itemsToShow.postValue(dbItemsToShow)
        try { // for network and api access
            val response = client.get(queryUrl(query))
            val stringResponse = response.bodyAsText()
//            println(stringResponse)
            val jsonResponse = stringResponse.toJsonObject()
            val jsonArray = jsonResponse["data"]?.jsonArray
            if (jsonArray?.firstOrNull()?.jsonObject?.get("movie_id") != null) {
                val movies: Data = response.body()
                insertToDb(movies.data)
                // todo remove deleted online items from database for this query
            }
        } catch (e: Exception) {
            e.message?.let { Log.i("API Search", it) }
        }
    }

    private fun insertToDb(data: List<Item>) {
        var dbChanged = false
        for (item in data) {
            val itemExist = itemRepo.isItemExist(item.movie_id)
            if (!itemExist) {
                itemRepo.insert(item)
                dbChanged = true
            }
        }
        if (dbChanged) {
            itemRepo.all()?.map {
                ItemToShow(
                    it.movie_id,
                    it.movie_title,
                    it.movie_title_en,
                    it.pic,
                    it.countries
                )
            }?.let { dbItemsToShow = it }
            itemsToShow.postValue(dbItemsToShow)
        }
    }
}