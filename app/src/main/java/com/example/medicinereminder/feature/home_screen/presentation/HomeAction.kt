package com.example.medicinereminder.feature.home_screen.presentation

import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.model.ReminderInfo

sealed interface HomeAction {
    data class UpdateTeb(val tab: HomeTab): HomeAction
    data class UpdateReminder(val reminder: ReminderInfo): HomeAction
    data object OpenBottomSheet: HomeAction
    data object CloseBottomSheet: HomeAction
    data class DeleteReminder(val reminder: ReminderInfo): HomeAction
    data class UpdateReminderState(val reminder: ReminderInfo,val newState: ReminderState): HomeAction
    data object AddNewReminder: HomeAction
    data class EditReminder(val reminder: ReminderInfo): HomeAction
    data class ViewDetails(val reminder: ReminderInfo): HomeAction
}