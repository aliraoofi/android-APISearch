package ir.solam.apisearch.model

class ItemRepositoryImpl(private val itemDao: ItemDao) : ItemRepository {
    override fun searchItemTitle(query: String) = itemDao.searchItemTitle(query)
    override fun searchItemCountry(query: String) = itemDao.searchItemCountry(query)
    override fun all(): List<Item>? = itemDao.all()
    override fun insert(item: Item) {
        itemDao.insert(item)
    }
    override fun delete(item: Item) {
        itemDao.delete(item)
    }
    override fun isItemExist(itemId: String): Boolean =
        itemDao.isItemExist(itemId)
}