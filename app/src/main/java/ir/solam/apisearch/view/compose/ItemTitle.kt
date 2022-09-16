package ir.solam.apisearch.view.compose

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import ir.solam.apisearch.model.ItemToShow
import ir.solam.apisearch.view.compose.constant.*

@Composable
fun ItemTitle(item: ItemToShow) {
    val title = item.movie_title
    BasicText(title, w50, textCenter)
}