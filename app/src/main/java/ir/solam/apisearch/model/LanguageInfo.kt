package ir.solam.apisearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class LanguageInfo(
    @ColumnInfo(name = "flag") val flag: String,
    @ColumnInfo(name = "text") val text: String
) : java.io.Serializable, Poly() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class LanguageInfoConverter {
    @TypeConverter
    fun fromLanguageInfo(languageInfo: LanguageInfo): String = encode(languageInfo)

    @TypeConverter
    fun toLanguageInfo(languageInfo: String): LanguageInfo = decode(languageInfo)
}