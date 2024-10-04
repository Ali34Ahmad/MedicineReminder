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
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.list_item.AppointmentItem
import com.example.medicinereminder.common.components.list_item.MedicineItem
import com.example.medicinereminder.common.components.tooltip.ToolTipCard
import com.example.medicinereminder.common.utility.extension.toFormattedString
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.model.ReminderInfo
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun HomeLazyColumn(
    modifier: Modifier = Modifier,
    reminders: List<ReminderInfo>,
    isRefillCardShown: Boolean,
    onRefillButtonClick: () -> Unit,
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
                        text = stringResource(id = R.string.restock_msg,3)
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium12))

                }
            }

            items(reminders, key = { it.reminder.id.toString() + it.type }) { reminder ->
                when (reminder.reminder) {
                    is MedicineReminder -> {
                        val form = reminder.pharmaceuticalForm?.name

                        reminder.medicine?.let { medicine ->
                            val isRunningLow = medicine.currentAmount <= 4
                            val isOutOfStock = medicine.currentAmount == 0
                            MedicineItem(
                                medicineName = medicine.name,
                                desc = if(isOutOfStock) stringResource(id = R.string.out_of_stock) else "${reminder.reminder.doseAmount} $form",
                                icon = if(isRunningLow) R.drawable.ic_error_outlined else null,
                                isWarning = isRunningLow
                            )
                        }
                    }

                    is Appointment -> {
                        reminder.doctor?.let { doctor ->
                            AppointmentItem(
                                doctorName = doctor.name,
                                speciality = doctor.specialty,
                                time = reminder.reminder.dateTime.toFormattedString(),
                                icon = null
                            )
                        }
                    }
                }
            }
        }

}