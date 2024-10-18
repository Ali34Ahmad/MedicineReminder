@file:OptIn(ExperimentalMaterial3Api::class)

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
import com.example.medicinereminder.common.components.list_item.BottomSheetItem
import com.example.medicinereminder.common.components.list_item.MedicineReminderItem
import com.example.medicinereminder.common.ext.extension.toTimeFormattedString
import com.example.medicinereminder.common.ext.extension.toLocalDateTime
import com.example.medicinereminder.common.model.BottomSheetInfo
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.local.medicine1
import com.example.medicinereminder.data.local.medicineForm1
import com.example.medicinereminder.data.local.relationship.MedicineWithFormAndConflicts
import com.example.medicinereminder.data.local.reminder1
import com.example.medicinereminder.data.model.MedicineWithReminder
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun MedicineReminderBottomSheet(
    modifier: Modifier = Modifier,
    reminderInfo: MedicineWithReminder,
    sheetState: SheetState,
    onDeleteButtonClick: (MedicineReminder) -> Unit,
    onViewButtonClick: (MedicineReminder) -> Unit,
    @StringRes viewButtonText: Int,
    @StringRes deleteButtonText: Int,
    onDismissRequest: () -> Unit,
    onMarkAsTakenButtonClick : (MedicineReminder) -> Unit,
    onMarkAsMissedButtonClick : (MedicineReminder) -> Unit,
    onEditButtonClick: (MedicineReminder) -> Unit,
    onStopReminderButtonClick: (MedicineReminder) -> Unit
) {
    val medicine = reminderInfo.medicineInfo.medicine
    val reminder = reminderInfo.reminder
    val medicineForm = reminderInfo.medicineInfo.form
    val interactions = reminderInfo.medicineInfo.interactions

    val items = listOf(
        BottomSheetInfo(
            onClick = {
                onMarkAsTakenButtonClick(reminder)
            },
            icon = R.drawable.ic_check,
            text = R.string.mark_as_taken
        ),
        BottomSheetInfo(
            onClick = {
                onMarkAsMissedButtonClick(reminder)
            },
            icon = R.drawable.ic_notification_missed_outlined,
            text = R.string.mark_as_missed
        ),
        BottomSheetInfo(
            onClick = {
                onEditButtonClick(reminder)
            },
            icon = R.drawable.ic_edit_outlined,
            text = R.string.edit
        ),
        BottomSheetInfo(
            onClick = {
                onStopReminderButtonClick(reminder)
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
    ){
        val suffix = if (reminder.doseAmount > 1) "s" else ""
        MedicineReminderItem(
            state = reminder.reminderState,
            medicineName = medicine.name,
            subtitle = "${reminder.doseAmount} ${medicineForm.name}$suffix",
            time = reminder.dateTime.toLocalDateTime().toTimeFormattedString(),
            conflictsNumber = interactions.size,
            image = medicine.imageFileName,
            onClick = {},
            defaultImage = R.drawable.pharmacy,
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
                onDeleteButtonClick(reminder)
            },
            onRightButtonClick = {
                onViewButtonClick(reminder)
            },
            leftButtonText = deleteButtonText,
            rightButtonText = viewButtonText,
            hasErrorColor = true
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MedicineReminderBottomSheetPreview() {
    MedicineReminderTheme {
        MedicineReminderBottomSheet(
            reminderInfo = MedicineWithReminder(reminder = reminder1, medicineInfo = MedicineWithFormAndConflicts(
                medicine1, medicineForm1, emptyList()
            )),
            sheetState = rememberModalBottomSheetState(),
            onDeleteButtonClick = {},
            onViewButtonClick = {},
            viewButtonText = R.string.view_details,
            deleteButtonText = R.string.delete_reminder,
            onDismissRequest = { /*TODO*/ },
            onMarkAsTakenButtonClick = {},
            onMarkAsMissedButtonClick ={},
            onEditButtonClick = {},
            onStopReminderButtonClick = {}
        )
    }
}