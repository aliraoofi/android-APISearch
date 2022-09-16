package ir.solam.apisearch.model

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun fromStringList(stringList: List<String>): String = encode(stringList)
//        stringList.joinToString(",")

    @TypeConverter
    fun toStringList(stringList: String): List<String> = decode(stringList)
//        stringList.split(",")
}