package ir.solam.apisearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Category(
    @ColumnInfo(name = "title_en") val title_en: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "link_type") val link_type: String,
    @ColumnInfo(name = "link_key") val link_key: String
) : java.io.Serializable, Poly() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class CategoriesConverter {
    @TypeConverter
    fun fromCategories(categories: List<Category>): String = encode(categories)

    @TypeConverter
    fun toCategories(categories: String): List<Category> = decode(categories)
}