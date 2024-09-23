package com.example.medicinereminder.common.components.dialog

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import org.hamcrest.Description

@Composable
fun DialogWithTitleAndDescription(
    showDialog: Boolean,
    @StringRes description: Int,
    @StringRes title: Int,
    @StringRes confirmButtonText: Int,
    @StringRes dismissButtonText: Int,
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (showDialog) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = onDismissRequest,
            title = { Text(text = stringResource(id = title)) },
            text = { Text(text = stringResource(id = description)) },
            confirmButton = {
                TextButton(onClick = onConfirmClick) {
                    Text(text = stringResource(id = confirmButtonText))
                }
            },
            dismissButton = {
                TextButton(onClick = onConfirmClick) {
                    Text(text = stringResource(id = dismissButtonText))
                }
            }
        )
    }
}

@DarkAndLightModePreview
@Composable
fun DialogWithTitleAndDescriptionPreview(){
    MedicineReminderTheme{
        Surface{
            DialogWithTitleAndDescription(
                showDialog = true,
                onDismissRequest = {},
                onConfirmClick = {},
                description = R.string.deletion_confirmation_text_singular,
                title =R.string.delete,
                confirmButtonText = R.string.confirm,
                dismissButtonText = R.string.cancel,
            )
        }
    }
}