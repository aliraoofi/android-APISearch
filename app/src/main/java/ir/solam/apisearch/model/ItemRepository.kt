package ir.solam.apisearch.model

interface ItemRepository {
    fun searchItemTitle(query: String): List<Item>?
    fun searchItemCountry(query: String): List<Item>?
    fun all(): List<Item>?
    fun insert(item: Item)
    fun delete(item: Item)
    fun isItemExist(itemId: String): Boolean
}