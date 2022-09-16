package ir.solam.apisearch

const val baseUrl = "https://www.filimo.com"
const val firstQuery = "پرواز"

fun queryRoute(query: String) = "/api/en/v1/movie/movie/list/tagid/1000300/text/$query/sug/on"
fun queryUrl(query: String) = baseUrl + queryRoute(query)
val searchFields = arrayOf("movie_title", "countries")