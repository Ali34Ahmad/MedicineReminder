package com.example.medicinereminder.feature.add_appointment.component.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.medicinereminder.R

@Composable
fun ReplaceDoctorDialogBox(
    doctorName: String,
    modifier: Modifier = Modifier,
    @StringRes cancelButtonText: Int = R.string.cancel,
    @StringRes confirmButtonText: Int = R.string.replace,
    @StringRes text: Int = R.string.replace_doctor_message,
    @StringRes title: Int = R.string.replace_doctor,
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    onConfirm: () -> Unit
){
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

            Text(text = stringResource(id = text,doctorName))
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