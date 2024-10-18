package com.example.medicinereminder.feature.appointment_screen.presentation.appointment_details_bootm_sheet

import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.local.entity.Appointment

sealed interface AppointmentDetailsBottomSheetAction {
    data class DeleteAppointment(val appointment: Appointment): AppointmentDetailsBottomSheetAction
    data class ViewDetails(val appointment: Appointment): AppointmentDetailsBottomSheetAction
    data class EditAppointment(val appointment: Appointment): AppointmentDetailsBottomSheetAction
    data class ChangeAppointmentState(val appointment: Appointment,val newState: ReminderState): AppointmentDetailsBottomSheetAction
}