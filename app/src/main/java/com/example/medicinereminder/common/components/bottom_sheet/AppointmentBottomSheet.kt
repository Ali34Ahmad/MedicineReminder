@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.medicinereminder.common.components.bottom_sheet

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.buttons.FooterButtons
import com.example.medicinereminder.common.components.list_item.AppointmentItem
import com.example.medicinereminder.common.components.list_item.BottomSheetItem
import com.example.medicinereminder.common.ext.extension.toTimeFormattedString
import com.example.medicinereminder.common.ext.extension.toLocalDateTime
import com.example.medicinereminder.common.model.BottomSheetInfo
import com.example.medicinereminder.data.local.appointment1
import com.example.medicinereminder.data.local.doctor1
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.model.DoctorWithAppointment
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun AppointmentBottomSheet(
    modifier: Modifier = Modifier,
    appointmentInfo: DoctorWithAppointment,
    sheetState: SheetState,
    onDeleteButtonClick: (Appointment) -> Unit,
    onViewButtonClick: (Appointment) -> Unit,
    @StringRes viewButtonText: Int,
    @StringRes deleteButtonText: Int,
    onDismissRequest: () -> Unit,
    onMarkAsTakenButtonClick : (Appointment) -> Unit,
    onMarkAsMissedButtonClick : (Appointment) -> Unit,
    onEditButtonClick: (Appointment) -> Unit,
    onStopReminderButtonClick: (Appointment) -> Unit
) {
    val doctor = appointmentInfo.doctor
    val appointment = appointmentInfo.appointment
    val items = listOf(
        BottomSheetInfo(
            onClick = {
                onMarkAsTakenButtonClick(appointment)
            },
            icon = R.drawable.ic_check,
            text = R.string.mark_as_taken
        ),
        BottomSheetInfo(
            onClick = {
                onMarkAsMissedButtonClick(appointment)
            },
            icon = R.drawable.ic_notification_missed_outlined,
            text = R.string.mark_as_missed
        ),
        BottomSheetInfo(
            onClick = {
                onEditButtonClick(appointment)
            },
            icon = R.drawable.ic_edit_outlined,
            text = R.string.edit
        ),
        BottomSheetInfo(
            onClick = {
                onStopReminderButtonClick(appointment)
            },
            icon = R.drawable.ic_notification_off_outlined,
            text = R.string.stop_reminder
        ),
    )
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {

        AppointmentItem(
            doctorName = doctor.name,
            speciality = doctor.specialty,
            time = appointment.dateTime.toLocalDateTime().toTimeFormattedString(),
            icon = null,
            onClick = {}
        )
        Spacer(
            modifier = Modifier.height(
                MaterialTheme.spacing.medium16
            )
        )
        items.forEach { item ->
            BottomSheetItem(
                data = item,
                modifier = Modifier.padding(
                    vertical = MaterialTheme.spacing.small1
                )
            )
        }
        FooterButtons(
            onLeftButtonClick = {
                onDeleteButtonClick(appointment)
            },
            onRightButtonClick = {
                onViewButtonClick(appointment)
            },
            leftButtonText = deleteButtonText,
            rightButtonText = viewButtonText,
            hasErrorColor = true
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AppointmentBottomSheetPreview() {
    MedicineReminderTheme {
        AppointmentBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            appointmentInfo = DoctorWithAppointment(appointment = appointment1, doctor = doctor1),
            sheetState = rememberModalBottomSheetState(),
            onDeleteButtonClick = {},
            onViewButtonClick = {},
            viewButtonText = R.string.view_details,
            deleteButtonText = R.string.delete,
            onDismissRequest = { /*TODO*/ },
            onMarkAsTakenButtonClick = {},
            onMarkAsMissedButtonClick = {},
            onEditButtonClick = {},
            onStopReminderButtonClick = {}
        )
    }
}