package ir.solam.apisearch.view.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import ir.solam.apisearch.view.compose.constant.roundedCornerShape
import ir.solam.apisearch.view.compose.constant.searchTextStyle
import ir.solam.apisearch.view.compose.constant.w50

@Composable
fun SearchField(
    field: String,
    onSearchChanged: (field: String, search: String) -> Unit
) {
    var search by rememberSaveable { mutableStateOf("") }
    BasicTextField(
        search,
        {
            search = it
            onSearchChanged(field, search)
        }, w50.background(Color.White, roundedCornerShape),
        textStyle = searchTextStyle,
    )
}