package com.example.medicinereminder.feature.settings.notifications.component.dialog_box

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.dialog.DialogWithSelectableItemsLazyColumn
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun NumberOfRepeatsList(
    modifier: Modifier = Modifier,
    repeats: List<Int> = MutableList(11){it}.toList(),
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    isDialogShown: Boolean,
    selectedItemIndex: Int = 3
) {

    var selectedItem = remember { mutableIntStateOf(selectedItemIndex) }
    DialogWithSelectableItemsLazyColumn(
        items = {
            items(repeats) { repeat ->
                val backgroundColor =
                if(repeat == selectedItem.intValue)
                    MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
                else MaterialTheme.colorScheme.surfaceContainer
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.medium16,)
                        .background(backgroundColor)
                        .padding(
                             MaterialTheme.spacing.small8,
                        )
                        .clickable{
                            selectedItem.intValue = repeat
                        },
                    text = repeat.toString(),
                )
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16)
                )
            }
        },
        title = R.string.number_of_repeats,
        description = R.string.choose_number_of_repeats,
        confirmButtonText = R.string.confirm,
        dismissButtonText = R.string.cancel,
        onDismissRequest = onDismissRequest,
        onConfirmClick = onConfirmClick,
        showDialog = isDialogShown,
        modifier = modifier,
        lazyListState = rememberLazyListState(),
    )
}

@Preview(showBackground = true)
@Composable
fun NumberOfRepeatsListPreview() {
    MedicineReminderTheme {
        NumberOfRepeatsList(
            onConfirmClick = {},
            onDismissRequest = {},
            isDialogShown = true
        )
    }
}