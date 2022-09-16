package ir.solam.apisearch.view.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import ir.solam.apisearch.model.ItemToShow


@Composable
fun ItemsList(items: List<ItemToShow>) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        items(items.size) { index ->
            ItemRow(items, index)
        }
    }
}