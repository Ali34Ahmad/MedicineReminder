package com.example.medicinereminder.feature.home_screen.presentation.reminder_bottom_sheet

import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.feature.home_screen.presentation.home_main.HomeBottomSheet

sealed interface MedicineReminderBottomSheetAction {
    data class DeleteMedicineReminder(val reminder: MedicineReminder): MedicineReminderBottomSheetAction
    data class EditMedicineReminder(val reminder: MedicineReminder): MedicineReminderBottomSheetAction
    data class ViewMedicineReminderDetails(val reminder: MedicineReminder): MedicineReminderBottomSheetAction
    data class ChangeMedicineReminderState(val reminder: MedicineReminder, val newState: ReminderState):
        MedicineReminderBottomSheetAction
}