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
fun DeleteAppointmentsDialogBox(
    modifier: Modifier = Modifier,
    appointmentsNumber: Int,
    @StringRes cancelButtonText: Int = R.string.cancel,
    @StringRes confirmButtonText: Int = R.string.delete_appointments,
    @StringRes text: Int = R.string.delete_appointments_message,
    @StringRes title: Int = R.string.delete_appintment,
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit ,
    onConfirm: () -> Unit
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
        text = {
            val message: String = if(appointmentsNumber != 1)
                stringResource(id = text,appointmentsNumber)
                else stringResource(id = R.string.delete_appointment_message)

                Text(text = message)
        },
        title = {
            Text(
                text = stringResource(id = title),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DeleteAppointmentsDialogBoxPreview() {
    MedicineReminderTheme {
        DeleteAppointmentsDialogBox(
            appointmentsNumber = 3,
            onDismissRequest = {},
            onCancel = {  },
            onConfirm = {}
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DeleteAppointmentDialogBoxPreview() {
    MedicineReminderTheme {
        DeleteAppointmentsDialogBox(
            appointmentsNumber = 1,
            onDismissRequest = {},
            onCancel = {  },
            onConfirm = {}
        )
    }
}