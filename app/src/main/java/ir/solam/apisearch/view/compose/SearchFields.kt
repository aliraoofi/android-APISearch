package ir.solam.apisearch.view.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import ir.solam.apisearch.searchFields
import ir.solam.apisearch.view.compose.constant.centerVertically
import ir.solam.apisearch.view.compose.constant.colorSkin
import ir.solam.apisearch.view.compose.constant.w100
import ir.solam.apisearch.view.compose.constant.spaceAround

@Composable
fun SearchFields(onSearchChanged: (field: String, search: String) -> Unit) {
    Row(
        w100
            .background(colorSkin)
            .padding(vertical = 4.dp), spaceAround, centerVertically
    ) {
        for (field in searchFields) {
            SearchField(field, onSearchChanged)
        }
    }
}