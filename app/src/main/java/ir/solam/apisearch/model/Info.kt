package ir.solam.apisearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Info(
    @ColumnInfo(name = "background_color") val background_color: String,
    @ColumnInfo(name = "text_color") val text_color: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "type") val type: String
) : java.io.Serializable, Poly() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}