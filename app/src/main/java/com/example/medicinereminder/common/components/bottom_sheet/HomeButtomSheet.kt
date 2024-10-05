package com.example.medicinereminder.common.components.bottom_sheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.model.BottomSheetInfo
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.remindersInfo
import com.example.medicinereminder.data.model.ReminderInfo
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomSheet(
    modifier: Modifier = Modifier,
    reminder: ReminderInfo,
    sheetState: SheetState,
    onMarkAsTakenButtonClick: (ReminderInfo) -> Unit,
    onMarkAsMissedButtonClick: (ReminderInfo) -> Unit,
    onEditButtonClick: (ReminderInfo) -> Unit,
    onStopReminderButtonClick: (ReminderInfo) -> Unit,
    onDeleteButtonClick: (ReminderInfo) -> Unit,
    onViewButtonClick: (ReminderInfo) -> Unit,
    onDismissRequest: () ->Unit
){
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
    CustomBottomSheetWithFooter(
        modifier = modifier,
        reminder = reminder,
        sheetState = sheetState,
        items = items,
        onDeleteButtonClick = onDeleteButtonClick,
        onViewButtonClick = onViewButtonClick,
        viewButtonText = R.string.view_details,
        deleteButtonText = R.string.delete_reminder,
        onDismissRequest = onDismissRequest
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeBottomSheetPreview() {
    MedicineReminderTheme {
        HomeBottomSheet(
            reminder = remindersInfo[0],
            sheetState = rememberModalBottomSheetState(false),
            onViewButtonClick = {},
            onEditButtonClick = {},
            onDeleteButtonClick = {},
            onMarkAsMissedButtonClick = {},
            onMarkAsTakenButtonClick = {},
            onStopReminderButtonClick = {},
            onDismissRequest = {}
        )
    }
}