package ir.solam.apisearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Subtitle(
    @ColumnInfo(name = "enable") val enable: Boolean,
    @ColumnInfo(name = "text") val text: String,
) : java.io.Serializable, Poly() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class SubtitleConverter {
    @TypeConverter
    fun fromSubtitle(subtitle: Subtitle): String = encode(subtitle)

    @TypeConverter
    fun toSubtitle(subtitle: String): Subtitle = decode(subtitle)
}