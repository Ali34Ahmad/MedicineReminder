package com.example.medicinereminder.common.components.lazy_column

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.medicinereminder.common.components.list_item.AppointmentItem
import com.example.medicinereminder.common.ext.extension.toTimeFormattedString
import com.example.medicinereminder.common.ext.extension.toLocalDateTime
import com.example.medicinereminder.data.model.DoctorWithAppointment

@Composable
fun AppointmentsLazyColumn(
    modifier: Modifier = Modifier,
    reminders: List<DoctorWithAppointment>,
    onItemSelected: (DoctorWithAppointment) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(reminders, key = {it.appointment.id}){ reminder->
            AppointmentItem(
                doctorName = reminder.doctor.name,
                speciality = reminder.doctor.specialty,
                time = reminder.dateTime.toLocalDateTime().toTimeFormattedString(),
                icon = null,
                onClick = {
                    onItemSelected(reminder)
                }
            )
        }
    }
}

















