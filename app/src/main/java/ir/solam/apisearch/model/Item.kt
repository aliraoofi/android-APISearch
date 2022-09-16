package ir.solam.apisearch.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import ir.solam.apisearch.viewmodel.MainViewModel
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Item(
    @NonNull @ColumnInfo(name = "link_type") val link_type: String,
    @NonNull @ColumnInfo(name = "link_key") val link_key: String,
    @NonNull @ColumnInfo(name = "theme") val theme: String,
    @NonNull @ColumnInfo(name = "output_type") val output_type: String,
    @NonNull @ColumnInfo(name = "movie_id") val movie_id: String,
    @NonNull @ColumnInfo(name = "uid") val uid: String,
    @NonNull @ColumnInfo(name = "movie_title") val movie_title: String,
    @NonNull @ColumnInfo(name = "movie_title_en") val movie_title_en: String,
    @ColumnInfo(name = "tag_id") val tag_id: String?,
    @NonNull @ColumnInfo(name = "serial") val serial: Serial,
    @NonNull @ColumnInfo(name = "watermark") val watermark: Boolean,
    @NonNull @ColumnInfo(name = "HD") val HD: Boolean,
    @NonNull @ColumnInfo(name = "watch_list_action") val watch_list_action: String,
    @NonNull @ColumnInfo(name = "commingsoon_txt") val commingsoon_txt: String,
    @NonNull @ColumnInfo(name = "rel_data") val rel_data: RelData,
    @NonNull @ColumnInfo(name = "badge") val badge: Badge,
    @NonNull @ColumnInfo(name = "rate_enable") val rate_enable: Boolean,
    @NonNull @ColumnInfo(name = "descr") val descr: String,
    @ColumnInfo(name = "cover") val cover: String?,
    @NonNull @ColumnInfo(name = "publish_date") val publish_date: String,
    @NonNull @ColumnInfo(name = "age_range") val age_range: String,
    @NonNull @ColumnInfo(name = "pic") val pic: Pic,
    @ColumnInfo(name = "rate_avrage") val rate_avrage: String?,
    @ColumnInfo(name = "avg_rate_label") val avg_rate_label: String?,
    @NonNull @ColumnInfo(name = "pro_year") val pro_year: String,
    @NonNull @ColumnInfo(name = "imdb_rate") val imdb_rate: String,
    @NonNull @ColumnInfo(name = "categories") val categories: List<Category>,
    @ColumnInfo(name = "duration") val duration: String?,
    @NonNull @ColumnInfo(name = "countries") val countries: List<Country>,
    @NonNull @ColumnInfo(name = "dubbed") val dubbed: Dubbed,
    @NonNull @ColumnInfo(name = "subtitle") val subtitle: Subtitle,
    @NonNull @ColumnInfo(name = "audio") val audio: Audio,
    @NonNull @ColumnInfo(name = "language_info") val language_info: LanguageInfo,
    @ColumnInfo(name = "director") val director: String?,
    @NonNull @ColumnInfo(name = "last_watch") val last_watch: List<String>, // todo check correct type if the server response had this object.
    @NonNull @ColumnInfo(name = "freemium") val freemium: Boolean,
    @NonNull @ColumnInfo(name = "position") val position: Int,
    @NonNull @ColumnInfo(name = "sid") val sid: Int,
    @NonNull @ColumnInfo(name = "uuid") val uuid: String
) : java.io.Serializable, Poly() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

data class ItemToShow(
    val movie_id: String,
    val movie_title: String,
    val movie_title_en: String,
    val pic: Pic,
    val countries: List<Country>
) : java.io.Serializable // added for parcelize

fun List<ItemToShow>.filter(
    field: String,
    search: String = "",
    viewModel: MainViewModel? = null
): List<ItemToShow> {
    if (this.isEmpty()) return listOf()
    val filteredItems = this.filter { item ->
        when (field) {
            "movie_title" -> {
                viewModel?.query?.postValue(search)
                item.movie_title.contains(search) ||
                        item.movie_title_en.contains(search, true)
            }
            "countries" -> item.countries.joinToString(",") { it.country }
                .contains(search) || item.countries.joinToString(",") { it.country_en }
                .contains(search, true)
            else -> true // for return all items
        }
    }
    return filteredItems
}