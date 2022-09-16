package ir.solam.apisearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Badge(
    @ColumnInfo(name = "backstage") val backstage: Boolean,
    @ColumnInfo(name = "vision") val vision: Boolean,
    @ColumnInfo(name = "hear") val hear: Boolean,
    @ColumnInfo(name = "online_release") val online_release: Boolean,
    @ColumnInfo(name = "free") val free: Boolean,
    @ColumnInfo(name = "exclusive") val exclusive: Boolean,
    @ColumnInfo(name = "commingsoon") val commingsoon: Boolean,
    @ColumnInfo(name = "info") val info: List<Info>
) : java.io.Serializable, Poly() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class BadgeConverter {
    @TypeConverter
    fun fromBadge(badge: Badge): String = encode(badge)

    @TypeConverter
    fun toBadge(badgeString: String): Badge = decode(badgeString)
}