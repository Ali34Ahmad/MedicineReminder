package com.example.medicinereminder.common.components.text_field

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.menu.MoreOptionsMenu
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.constants.MenuItems
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.model.MoreOptionMenuItem
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OutlinedTextFieldWithDropDownMenu(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {},
    @StringRes label: Int,
    @StringRes errorMessage: Int? = null,
    isRequired: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    enabled: Boolean = true,
    readOnly: Boolean = true,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onItemClick: (index: Int) -> Unit,
    menuItems: List<MoreOptionMenuItem>,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val trailingIcon = if (expanded) Icons.Outlined.ArrowDropUp else Icons.Outlined.ArrowDropDown
    val textFieldColors =
        if (enabled)
            OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurface,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
            )
        else
            OutlinedTextFieldDefaults.colors()
    Box(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            isError = errorMessage != null,
            enabled = false,
            colors = textFieldColors,
            readOnly = readOnly,
            maxLines = 1,
            label = {
                Text(
                    text = if (isRequired)
                        stringResource(label).plus(" *")
                    else stringResource(label)
                )
            },
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = trailingIcon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            modifier = Modifier
                .combinedClickable(
                    onClick = { onExpandChange(true) }
                )
                .indication(indication = null, interactionSource = interactionSource)
        )
        MoreOptionsMenu(
            menuItems = menuItems,
            expanded = expanded,
            onDismissRequest = { onExpandChange(false) },
            onItemClick = { index ->
                onItemClick(index)
                onExpandChange(false)
            },
        )
    }
}

@DarkAndLightModePreview
@Composable
fun MedicineReminderTextFieldPreview1() {
    MedicineReminderTheme {
        Surface {
            OutlinedTextFieldWithDropDownMenu(
                value = "Hello",
                onValueChange = {},
                label = R.string.medicine_name,
                isRequired = true,
                expanded = false,
                menuItems = MenuItems.durationUnits(),
                onItemClick = {},
                onExpandChange = {},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun MedicineReminderTextFieldWithTextActionPreview2() {
    MedicineReminderTheme {
        Surface {
            OutlinedTextFieldWithDropDownMenu(
                value = "Hello",
                onValueChange = {},
                label = R.string.medicine_name,
                isRequired = true,
                expanded = true,
                menuItems = MenuItems.durationUnits(),
                onItemClick = {},
                onExpandChange = {},
            )
        }
    }
}