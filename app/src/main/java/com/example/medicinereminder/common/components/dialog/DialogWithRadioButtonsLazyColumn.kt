package com.example.medicinereminder.common.components.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.list_item.RadioButtonItem
import com.example.medicinereminder.data.local.interaction
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.model.SelectableItem
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing


@Composable
fun DialogWithRadioButtonLazyColumn(
    showDialog: Boolean,
    @StringRes description: Int,
    @StringRes title: Int,
    selectableItems: List<SelectableItem>,
    @StringRes confirmButtonText: Int,
    @StringRes dismissButtonText: Int,
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    onCheckedChange: (index: Int, checked: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    DialogWithSelectableItemsLazyColumn(
        showDialog = showDialog,
        description = description,
        title = title,
        confirmButtonText = confirmButtonText,
        dismissButtonText = dismissButtonText,
        onDismissRequest = onDismissRequest,
        onConfirmClick = onConfirmClick,
        lazyListState = lazyListState,
        modifier = modifier,
    ) {
        items(selectableItems.size) { index ->
            RadioButtonItem(
                selected = selectableItems[index].selected,
                text = selectableItems[index].value,
                onClick = {
                    if (!selectableItems[index].selected) {
                        onCheckedChange(index, !selectableItems[index].selected)
                    }
                },
                style = MaterialTheme.typography.bodyMedium,
                contentPadding=PaddingValues(
                    start = MaterialTheme.spacing.medium12,
                    end = MaterialTheme.spacing.medium16,
                    ),
                textPadding = PaddingValues(start = MaterialTheme.spacing.small4),
                )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun DialogWithRadioButtonLazyColumnPreview() {
    val items = remember {
        mutableStateListOf(
            SelectableItem(interaction.description, false),
            SelectableItem(interaction.description, false),
            SelectableItem(interaction.description, false),
            SelectableItem(interaction.description, false),
            SelectableItem(interaction.description, false),
            SelectableItem(interaction.description, false),
        )
    }
    MedicineReminderTheme {
        Surface {
            DialogWithRadioButtonLazyColumn(
                showDialog = true,
                onDismissRequest = {},
                onConfirmClick = {},
                title = R.string.edit_interactions,
                confirmButtonText = R.string.confirm,
                dismissButtonText = R.string.cancel,
                description = R.string.choose_which_one_to_edit,
                selectableItems = items,
                onCheckedChange = { index, checked ->
                    items.forEachIndexed { i, item ->
                        items[i] = item.copy(selected = i == index)
                    }
                    items[index] = items[index].copy(selected = checked)
                }
            )
        }
    }
}