package com.example.medicinereminder.feature.appointment_screen.presentation

import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.model.ReminderInfo

sealed interface AppointmentsAction {
    data object OpenBottomSheet : AppointmentsAction
    data object CloseBottomSheet: AppointmentsAction
    data class UpdateCurrentTab(val tab: AppointmentsTab) : AppointmentsAction
    data class UpdateSelectedReminder(val reminder: ReminderInfo): AppointmentsAction
    data class UpdateAppointment(val appointment: Appointment): AppointmentsAction
    data class UpdateAppointmentState(val appointment: Appointment,val state: ReminderState):
        AppointmentsAction
    data class DeleteAppointment(val appointment: Appointment): AppointmentsAction
}