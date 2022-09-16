package ir.solam.apisearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class RelData(
    @ColumnInfo(name = "rel_type") val rel_type: String?,
    @ColumnInfo(name = "rel_id") val rel_id: String?,
    @ColumnInfo(name = "rel_uid") val rel_uid: String?,
    @ColumnInfo(name = "rel_title") val rel_title: String?,
    @ColumnInfo(name = "rel_type_txt") val rel_type_txt: String
) : java.io.Serializable, Poly() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class RelDataConverter {
    @TypeConverter
    fun fromRelData(relData: RelData): String = encode(relData)

    @TypeConverter
    fun toRelData(relData: String): RelData = decode(relData)
}