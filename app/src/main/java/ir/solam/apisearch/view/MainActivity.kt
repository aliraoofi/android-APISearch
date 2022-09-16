package ir.solam.apisearch.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import ir.solam.apisearch.ioDispatcher
import ir.solam.apisearch.model.ItemToShow
import ir.solam.apisearch.model.filter
import ir.solam.apisearch.searchFields
import ir.solam.apisearch.view.compose.*
import ir.solam.apisearch.view.compose.constant.centerHorizontally
import ir.solam.apisearch.view.compose.constant.colorMute
import ir.solam.apisearch.view.compose.constant.fillSize
import ir.solam.apisearch.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.query.observe(this@MainActivity) {
            lifecycleScope.launch(ioDispatcher) {
                viewModel.getAndSaveData(it)
            }
        }
        setContent {
            val observedItems by viewModel.itemsToShow.observeAsState(listOf())
            var rememberItems: List<ItemToShow> by rememberSaveable { mutableStateOf(listOf()) }
            var filteredItems = observedItems
            val fieldsMap = searchFields.associateWith { "" }
            var rememberSearchFields: Map<String, String> by rememberSaveable {
                mutableStateOf(
                    fieldsMap
                )
            }
            val onSearchChanged = { field: String, search: String ->
                val searchFieldsMap: MutableMap<String, String> =
                    rememberSearchFields.toMutableMap()
                searchFieldsMap[field] = search
                rememberSearchFields = searchFieldsMap
            }
            for ((field, search) in rememberSearchFields) {
                filteredItems = filteredItems.filter(field, search, viewModel)
            }
            rememberItems = filteredItems.sortedBy { it.movie_title }
            Column(fillSize.background(colorMute), Arrangement.Top, centerHorizontally) {
                SearchFields(onSearchChanged)
//                if (rememberItems.isNotEmpty()) {
                ItemsList(rememberItems)
//                }
            }
        }
    }
}