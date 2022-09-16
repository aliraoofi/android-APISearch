package ir.solam.apisearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Pic(
    @ColumnInfo(name = "movie_img_s") val movie_img_s: String,
    @ColumnInfo(name = "movie_img_m") val movie_img_m: String,
    @ColumnInfo(name = "movie_img_b") val movie_img_b: String
) : java.io.Serializable, Poly() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class PicConverter {
    @TypeConverter
    fun fromPic(pic: Pic): String = encode(pic)

    @TypeConverter
    fun toPic(pic: String): Pic = decode(pic)
}