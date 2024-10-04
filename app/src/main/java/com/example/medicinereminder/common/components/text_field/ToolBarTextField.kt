package com.example.medicinereminder.common.components.text_field

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.R

@Composable
fun ToolbarTextField(
    searchText:String,
    onSearchTextValueChange:(String)->Unit,
    @StringRes hintText:Int,
    modifier: Modifier = Modifier,
    ) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextValueChange,
        modifier = modifier
            .fillMaxHeight(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        maxLines = 1,
        placeholder = {
            Text(
                text = stringResource(id = hintText),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        textStyle = MaterialTheme.typography.bodyLarge
    )
}