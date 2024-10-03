package com.example.medicinereminder.common.components.top_app_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun TopAppBarWithTrailingIconButton(
    title: String,
    buttonIcon: @Composable () -> Unit,
    textFieldIsShowing: Boolean,
    searchText: String,
    onSearchTextValueChange: (String) -> Unit,
    onSearchIconClick: () -> Unit,
    showNavigateUpButton: Boolean,
    onNavigateUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBarWrapper(
        modifier = modifier,
        hasLeadingIcon = showNavigateUpButton,
        containsTextField = textFieldIsShowing,
    ) {
        if (showNavigateUpButton && !textFieldIsShowing) {
            IconButton(onClick = onNavigateUpClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(Icons.Outlined.ArrowBack),
                    contentDescription = stringResource(id = R.string.go_back)
                )
            }
        }
        if (!textFieldIsShowing) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = MaterialTheme.spacing.small8),
            )
            IconButton(onClick = onSearchIconClick) {
                buttonIcon()
            }
        } else {
            TopAppBarWithTextField(
                searchText = searchText,
                onSearchTextValueChange = onSearchTextValueChange,
                onNavigateUpClick = {},
                onClearButtonClick = {},
            )
        }
    }
}

@Composable
private fun TopAppBarWithTextField(
    searchText: String,
    onSearchTextValueChange: (String) -> Unit,
    onNavigateUpClick: () -> Unit,
    onClearButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        IconButton(onClick = onNavigateUpClick) {
            Icon(
                imageVector = ImageVector.vectorResource(Icons.Outlined.ArrowBack),
                contentDescription = stringResource(id = R.string.close_search)
            )
        }
        TextField(
            value = searchText,
            onValueChange = onSearchTextValueChange,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
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
                    text = stringResource(id = R.string.name_or_specialty),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            textStyle = MaterialTheme.typography.bodyLarge
        )
        IconButton(onClick = onClearButtonClick) {
            Icon(
                imageVector = ImageVector.vectorResource(Icons.Outlined.Close),
                contentDescription = stringResource(id = R.string.clear)
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TopAppBarWithTitleAndSearchIconButtonPreview() {
    MedicineReminderTheme {
        Surface {
            TopAppBarWithTrailingIconButton(
                title = stringResource(id = R.string.app_name),
                textFieldIsShowing = false,
                searchText = "Sophie ",
                onSearchTextValueChange = {},
                showNavigateUpButton = false,
                onSearchIconClick = {},
                onNavigateUpClick = {},
                buttonIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(Icons.Outlined.Search),
                        contentDescription = stringResource(R.string.search)
                    )
                },
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TopAppBarWithTitleAndSearchWithNavigateUpIconButtonPreview() {
    MedicineReminderTheme {
        Surface {
            TopAppBarWithTrailingIconButton(
                title = stringResource(id = R.string.app_name),
                textFieldIsShowing = false,
                searchText = "Sophie ",
                onSearchTextValueChange = {},
                showNavigateUpButton = true,
                onSearchIconClick = {},
                onNavigateUpClick = {},
                buttonIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(Icons.Outlined.Search),
                        contentDescription = stringResource(R.string.search)
                    )
                },
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun TopAppBarWithTitleAndSearchIconButtonWithTextFieldEmptyTextPreview() {
    MedicineReminderTheme {
        Surface {
            TopAppBarWithTrailingIconButton(
                title = stringResource(id = R.string.app_name),
                textFieldIsShowing = true,
                searchText = "",
                onSearchTextValueChange = {},
                showNavigateUpButton = true,
                onSearchIconClick = {},
                onNavigateUpClick = {},
                buttonIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(Icons.Outlined.Search),
                        contentDescription = stringResource(R.string.search)
                    )
                }
            )
        }
    }
}

//TODO("Repair the error in the text field")
@DarkAndLightModePreview
@Composable
fun TopAppBarWithTitleAndSearchIconButtonWithTextFieldPreview() {
    MedicineReminderTheme {
        Surface {
            TopAppBarWithTrailingIconButton(
                title = stringResource(id = R.string.app_name),
                textFieldIsShowing = true,
                searchText = "Sophie",
                onSearchTextValueChange = {},
                showNavigateUpButton = true,
                onSearchIconClick = {},
                onNavigateUpClick = {},
                buttonIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(Icons.Outlined.Search),
                        contentDescription = stringResource(R.string.search)
                    )
                }
            )
        }
    }
}

