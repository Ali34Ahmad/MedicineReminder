package com.example.medicinereminder.common.components.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.check_box.CheckBoxComponent
import com.example.medicinereminder.data.local.conflict
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.model.SelectableItem
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing


@Composable
fun DialogWithCheckBoxesLazyColumn(
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
            CheckBoxComponent(
                checked = selectableItems[index].selected,
                text = selectableItems[index].value.description,
                onCheckedChange = {
                    onCheckedChange(index, it)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(
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
fun DialogWithCheckBoxesPreview() {
    val items = remember {
        mutableStateListOf(
            SelectableItem(conflict, false),
            SelectableItem(conflict, false),
            SelectableItem(conflict, false),
            SelectableItem(conflict, false),
            SelectableItem(conflict, false),
            SelectableItem(conflict, false),
        )
    }
    MedicineReminderTheme {
        Surface {
            DialogWithCheckBoxesLazyColumn(
                showDialog = true,
                onDismissRequest = {},
                onConfirmClick = {},
                title = R.string.remove_interactions,
                confirmButtonText = R.string.confirm,
                dismissButtonText = R.string.cancel,
                description = R.string.choose_which_items_to_remove,
                selectableItems = items,
                onCheckedChange = { index, checked ->
                    items[index] = items[index].copy(selected = checked)
                }
            )
        }
    }
}