package com.example.medicinereminder.common.components.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing


@Composable
fun DialogWithSelectableItemsLazyColumn(
    showDialog: Boolean,
    @StringRes description: Int,
    @StringRes title: Int,
    @StringRes confirmButtonText: Int,
    @StringRes dismissButtonText: Int,
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    items:LazyListScope.()->Unit={},
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismissRequest,
        ) {
            Column(
                modifier = modifier
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = RoundedCornerShape(MaterialTheme.spacing.medium28)
                    )
            ) {
                DialogHeader(title = title, description = description,
                    modifier = Modifier.padding(
                        top = MaterialTheme.spacing.medium24,
                        start = MaterialTheme.spacing.medium24,
                        end = MaterialTheme.spacing.medium24,
                        ),
                    )
                LazyColumnWithScrollBar(
                    lazyListState = lazyListState,
                    modifier = Modifier.padding(end = MaterialTheme.spacing.medium24)
                ){
                    items()
                }
                DialogActions(
                    dismissButtonText = dismissButtonText,
                    confirmButtonText = confirmButtonText,
                    onConfirmClick = onConfirmClick,
                    onDismissRequest = onDismissRequest,
                    modifier = Modifier.padding(
                        bottom = MaterialTheme.spacing.medium24,
                        start = MaterialTheme.spacing.medium24,
                        end = MaterialTheme.spacing.medium24,
                    ),
                )
            }
        }
    }
}


@DarkAndLightModePreview
@Composable
fun DialogWithRadioGroupPreview() {
    MedicineReminderTheme {
        Surface {
            DialogWithSelectableItemsLazyColumn(
                showDialog = true,
                onDismissRequest = {},
                onConfirmClick = {},
                title = R.string.remove_interactions,
                confirmButtonText = R.string.confirm,
                dismissButtonText = R.string.cancel,
                description = R.string.choose_which_items_to_remove,
            )
        }
    }
}