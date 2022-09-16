package ir.solam.apisearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Dubbed(
    @ColumnInfo(name = "enable") val enable: Boolean,
    @ColumnInfo(name = "text") val text: String
) : java.io.Serializable, Poly() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class DubbedConverter {
    @TypeConverter
    fun fromDubbed(dubbed: Dubbed): String = encode(dubbed)

    @TypeConverter
    fun toDubbed(dubbed: String): Dubbed = decode(dubbed)
}