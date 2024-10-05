package com.example.medicinereminder.common.components.lazy_column

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.bottom_sheet.HomeBottomSheet
import com.example.medicinereminder.common.components.list_item.AppointmentItem
import com.example.medicinereminder.common.components.list_item.MedicineReminderItem
import com.example.medicinereminder.common.components.tooltip.ToolTipCard
import com.example.medicinereminder.common.utility.extension.toFormattedString
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.model.Reminder
import com.example.medicinereminder.data.model.ReminderInfo
import com.example.medicinereminder.presentation.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeLazyColumn(
    modifier: Modifier = Modifier,
    reminders: List<ReminderInfo>,
    isRefillCardShown: Boolean,
    onRefillButtonClick: () -> Unit,
    onItemSelected: (ReminderInfo) -> Unit,
    sheetState: SheetState
    ) {
    val scope = rememberCoroutineScope()
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
                        reminder.medicine?.let { medicine ->
                            val suffix = if(reminder.reminder.doseAmount>1) "s" else ""
                            MedicineReminderItem(
                                state = reminder.reminder.reminderState,
                                medicineName = medicine.name,
                                subtitle = "${reminder.reminder.doseAmount} ${reminder.pharmaceuticalForm?.name}$suffix",
                                time = reminder.reminder.dateTime.toFormattedString(),
                                conflictsNumber = reminder.conflicts.size ,
                                image = medicine.imageFileName,
                                onClick = {
                                   scope.launch {
                                       onItemSelected(reminder)
                                       sheetState.show()
                                   }
                                },
                                defaultImage = R.drawable.pharmacy,
                            )
                        }
                    }

                    is Appointment -> {
                        reminder.doctor?.let { doctor ->
                            AppointmentItem(
                                doctorName = doctor.name,
                                speciality = doctor.specialty,
                                time = reminder.reminder.dateTime.toFormattedString(),
                                icon = null,
                                onClick = {
                                    scope.launch {
                                        onItemSelected(reminder)
                                        sheetState.show()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
}