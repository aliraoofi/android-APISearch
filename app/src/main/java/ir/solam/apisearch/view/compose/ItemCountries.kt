package ir.solam.apisearch.view.compose

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import ir.solam.apisearch.model.ItemToShow
import ir.solam.apisearch.view.compose.constant.*

@Composable
fun ItemCountries(item: ItemToShow) {
    val countries = item.countries.joinToString(" ") {
        it.country
    }
    BasicText(countries, w50, textCenter)
}