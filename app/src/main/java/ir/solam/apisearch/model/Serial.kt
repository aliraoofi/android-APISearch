package ir.solam.apisearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Serial(
    @ColumnInfo(name = "enable") val enable: Boolean,
    @ColumnInfo(name = "parent_title") val parent_title: String,
    @ColumnInfo(name = "season_id") val season_id: Int,
    @ColumnInfo(name = "serial_part") val serial_part: String,
    @ColumnInfo(name = "part_text") val part_text: String,
    @ColumnInfo(name = "season_text") val season_text: String,
    @ColumnInfo(name = "last_part") val last_part: String
) : java.io.Serializable, Poly() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class SerialConverter {
    @TypeConverter
    fun fromSerial(serial: Serial): String = encode(serial)

    @TypeConverter
    fun toSerial(serial: String): Serial = decode(serial)
}