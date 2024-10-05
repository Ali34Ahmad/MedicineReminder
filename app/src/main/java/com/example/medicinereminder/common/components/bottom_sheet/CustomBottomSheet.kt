package com.example.medicinereminder.common.components.bottom_sheet

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
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
import com.example.medicinereminder.common.components.list_item.MedicineReminderItem
import com.example.medicinereminder.common.model.BottomSheetInfo
import com.example.medicinereminder.common.utility.extension.toFormattedString
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.local.remindersInfo
import com.example.medicinereminder.data.model.ReminderInfo
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheetWithFooter(
    modifier: Modifier = Modifier,
    reminder: ReminderInfo,
    sheetState: SheetState,
    items: List<BottomSheetInfo>,
    onDeleteButtonClick: (ReminderInfo) -> Unit,
    onViewButtonClick: (ReminderInfo) -> Unit,
    @StringRes viewButtonText:Int,
    @StringRes deleteButtonText: Int,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium12))
        when(reminder.reminder){
            is MedicineReminder ->{
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

                        },
                        defaultImage = R.drawable.pharmacy,
                    )
                }
            }
            is Appointment ->{
                reminder.doctor?.let { doctor ->
                    AppointmentItem(
                        doctorName = doctor.name,
                        speciality = doctor.specialty,
                        time = reminder.reminder.dateTime.toFormattedString(),
                        icon = null,
                        onClick = {}
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(
            MaterialTheme.spacing.medium16
        ))
        items.forEach { data ->
            BottomSheetItem(
                data = data,
                modifier = Modifier.padding(
                    vertical = MaterialTheme.spacing.small1
                )
                )
        }
        FooterButtons(
            onLeftButtonClick = {
                onDeleteButtonClick(reminder)
            },
            onRightButtonClick = {
                onViewButtonClick(reminder)
            },
            leftButtonText = deleteButtonText,
            rightButtonText = viewButtonText
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomBottomSheetPreview() {
    MedicineReminderTheme {
        CustomBottomSheetWithFooter(
            reminder = remindersInfo[1],
            sheetState = rememberModalBottomSheetState(true),
            onViewButtonClick = {},
            onDeleteButtonClick = {},
            items = listOf(
                BottomSheetInfo(
                    onClick = {},
                    icon = R.drawable.ic_check,
                    text = R.string.mark_as_taken
                ),
                BottomSheetInfo(
                    onClick = {},
                    icon = R.drawable.ic_notification_missed_outlined,
                    text = R.string.mark_as_missed
                ),
                BottomSheetInfo(
                    onClick = {},
                    icon = R.drawable.ic_edit_outlined,
                    text = R.string.edit_date_and_time
                ),
                BottomSheetInfo(
                    onClick = {},
                    icon = R.drawable.ic_notification_off_outlined,
                    text = R.string.stop_reminder
                ),
            ),
            deleteButtonText = R.string.delete_appintment,
            viewButtonText = R.string.view_details,
            onDismissRequest = {}
        )
    }
}


