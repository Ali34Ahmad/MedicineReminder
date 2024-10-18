package com.example.medicinereminder.feature.doctor_details.component.dialog_box

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun StopAppointmentsDialogBox(
    modifier: Modifier = Modifier,
    doctorName: String,
    @StringRes title: Int = R.string.stop_reminders,
    @StringRes text: Int = R.string.stop_reminders_message,
    @StringRes confirmButtonText: Int = R.string.stop_reminders,
    @StringRes cancelButtonText: Int = R.string.cancel,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    onCancel : () -> Unit
    ) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
                onDismissRequest()
            }) {
                Text(text = stringResource(id = confirmButtonText))
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text(text = stringResource(id = cancelButtonText))
            }
        },
        title = {
            Text(
                text = stringResource(id = title),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(text = stringResource(id = text,doctorName))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun StopRemindersDialogBoxPreview() {
    MedicineReminderTheme {
        StopAppointmentsDialogBox(
            doctorName = "Emad Ahmad",
            onDismissRequest = { /*TODO*/ },
            onConfirm = { /*TODO*/ }) {
        }
    }
}