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
import com.example.medicinereminder.common.components.text_field.OutlinedTextFieldComponent
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun DialogWithTextField(
    showDialog: Boolean,
    @StringRes title: Int,
    @StringRes textFieldLabel: Int,
    textFieldValue: String,
    onValueChange:(String)->Unit,
    @StringRes confirmButtonText: Int,
    @StringRes dismissButtonText: Int,
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes errorMessage:Int?=null,
) {
    if (showDialog) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = onDismissRequest,
            title = { Text(text = stringResource(id = title)) },
            text = {
                OutlinedTextFieldComponent(
                    label = textFieldLabel,
                    value = textFieldValue,
                    onValueChange = onValueChange,
                    errorMessage = errorMessage
                )
            },
            confirmButton = {
                TextButton(onClick = onConfirmClick,
                    enabled = textFieldValue.isNotBlank()) {
                    Text(text = stringResource(id = confirmButtonText))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = stringResource(id = dismissButtonText))
                }
            }
        )
    }
}

@DarkAndLightModePreview
@Composable
fun DialogWithTextFieldPreview(){
    MedicineReminderTheme{
        Surface{
            DialogWithTextField(
                showDialog = true,
                onDismissRequest = {},
                onConfirmClick = {},
                textFieldLabel = R.string.form_name,
                textFieldValue = "Cream",
                title =R.string.add_new_form,
                confirmButtonText = R.string.add,
                dismissButtonText = R.string.cancel,
                onValueChange = {},
            )
        }
    }
}