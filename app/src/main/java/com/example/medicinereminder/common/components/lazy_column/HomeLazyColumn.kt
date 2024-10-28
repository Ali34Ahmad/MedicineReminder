package com.example.medicinereminder.common.components.lazy_column

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.cards.ToolTipCard
import com.example.medicinereminder.common.components.list_item.AppointmentItem
import com.example.medicinereminder.common.components.list_item.MedicineReminderItem
import com.example.medicinereminder.common.ext.extension.toTimeFormattedString
import com.example.medicinereminder.common.ext.extension.toLocalDateTime
import com.example.medicinereminder.data.local.timedEvents
import com.example.medicinereminder.data.model.DoctorWithAppointment
import com.example.medicinereminder.data.model.MedicineWithReminder
import com.example.medicinereminder.data.model.TimedEvent
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun HomeLazyColumn(
    modifier: Modifier = Modifier,
    reminders: List<TimedEvent>,
    isRefillCardShown: Boolean,
    onRefillButtonClick: () -> Unit,
    onItemSelected: (TimedEvent) -> Unit,
    numberOfMedicinesRunningLow: Int = 0
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isRefillCardShown) {
            item {
                ToolTipCard(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16),
                    onClick = onRefillButtonClick,
                    icon = R.drawable.ic_bulb_outlined,
                    trailingIcon = R.drawable.ic_arrow_right,
                    text = stringResource(id = R.string.restock_msg,numberOfMedicinesRunningLow )
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium12))

            }
        }

        items(reminders, key = { it.id }) { reminder ->
            when (reminder) {
                is MedicineWithReminder -> {
                    val suffix = if (reminder.reminder.doseAmount > 1) "s" else ""
                    MedicineReminderItem(
                        state = reminder.reminder.reminderState,
                        medicineName = reminder.medicineInfo.medicine.name,
                        subtitle = "${reminder.reminder.doseAmount} ${reminder.medicineInfo.form.name}$suffix",
                        time = reminder.reminder.dateTime.toLocalDateTime().toTimeFormattedString(),
                        conflictsNumber = reminder.medicineInfo.interactions.size,
                        image = reminder.medicineInfo.medicine.imageFileName,
                        onClick = {
                            onItemSelected(reminder)
                        },
                        defaultImage = R.drawable.pharmacy,
                    )

                }

                is DoctorWithAppointment -> {
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
    }
}

@Preview(showBackground = true)
@Composable
fun HomeLazyColumnPreview() {
    MedicineReminderTheme {
        HomeLazyColumn(
            reminders = timedEvents.sortedBy { it.dateTime },
            isRefillCardShown = true ,
            onRefillButtonClick = {  },
            onItemSelected = {},
        )
    }
}