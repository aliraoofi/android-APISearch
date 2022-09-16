package ir.solam.apisearch.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val polymorphicSerializers = SerializersModule {
    polymorphic(Poly::class) {
        subclass(Audio::class)
        subclass(Badge::class)
        subclass(Category::class)
        subclass(Country::class)
        subclass(Data::class)
        subclass(Dubbed::class)
        subclass(Info::class)
        subclass(Item::class)
        subclass(LanguageInfo::class)
        subclass(Pic::class)
        subclass(RelData::class)
        subclass(Serial::class)
        subclass(Subtitle::class)
    }
}

val deCodeFormat = Json {
    classDiscriminator = "#class"
    serializersModule = polymorphicSerializers
    isLenient = true // accept "1" instead of 1 & ...
    ignoreUnknownKeys = true // ignore type parameter in this project case
}

inline fun <reified T> decode(data: String): T = deCodeFormat.decodeFromString(data)


val enCodeFormat = Json {
    serializersModule = polymorphicSerializers
    classDiscriminator = "#class"
//    explicitNulls = false
}

inline fun <reified T> encode(data: T) = enCodeFormat.encodeToString(data)

fun toJsonElement(string: String) = Json.parseToJsonElement(string)
fun String.toJsonObject() = toJsonElement(this).jsonObject
//fun String.toJsonArray() = toJsonElement(this).jsonArray