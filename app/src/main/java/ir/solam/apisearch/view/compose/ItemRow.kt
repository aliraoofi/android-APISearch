package ir.solam.apisearch.view.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import ir.solam.apisearch.model.ItemToShow
import ir.solam.apisearch.view.compose.constant.*

@Composable
fun ItemRow(items: List<ItemToShow>, index: Int) {
    val rowColor = if (index % 2 == 0) colorYellowLight else colorOrangeLight
    Row(w100.background(rowColor), spaceAround, centerVertically) {
        val item = items[index]
        ItemTitle(item)
        ItemCountries(item)
    }
}