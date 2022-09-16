package ir.solam.apisearch.model

import androidx.room.*

@Dao
interface ItemDao {
    @Query("SELECT * FROM item WHERE (movie_title LIKE '%'+:query+'%' OR movie_title_en LIKE '%'+:query+'%')")
    fun searchItemTitle(query: String): List<Item>?

    @Query("SELECT * FROM item WHERE (countries LIKE '%'+:query+'%')")
    fun searchItemCountry(query: String): List<Item>?

    @Query("SELECT * FROM item")
    fun all(): List<Item>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("SELECT EXISTS(SELECT * FROM item WHERE movie_id = :itemId)")
    fun isItemExist(itemId: String): Boolean
}