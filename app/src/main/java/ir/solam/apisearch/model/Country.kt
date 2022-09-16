package ir.solam.apisearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Country(
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "country_en") val country_en: String
) : java.io.Serializable, Poly() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class CountriesConverter {
    @TypeConverter
    fun fromCountries(countries: List<Country>): String = encode(countries)

    @TypeConverter
    fun toCountries(countries: String): List<Country> = decode(countries)
}