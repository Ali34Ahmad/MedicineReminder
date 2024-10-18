package com.example.medicinereminder.feature.home_screen.presentation.appointment_bottom_sheet

import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.feature.home_screen.presentation.home_main.HomeBottomSheet

sealed interface AppointmentBottomSheetAction {
    data class DeleteAppointment(val appointment: Appointment): AppointmentBottomSheetAction
    data class EditAppointment(val appointment: Appointment): AppointmentBottomSheetAction
    data class ViewAppointmentDetails(val appointment: Appointment): AppointmentBottomSheetAction
    data class ChangeAppointmentState(val appointment: Appointment,val newState: ReminderState): AppointmentBottomSheetAction
}