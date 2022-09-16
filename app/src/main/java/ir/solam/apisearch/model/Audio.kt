package ir.solam.apisearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Audio(
    @ColumnInfo(name = "languages") val languages: List<String>,
    @ColumnInfo(name = "isMultiLanguage") val isMultiLanguage: Boolean
) : java.io.Serializable, Poly() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class AudioConverter {
    @TypeConverter
    fun fromAudio(audio: Audio): String = encode(audio)

    @TypeConverter
    fun toAudio(audio: String): Audio = decode(audio)
}