package com.example.medicinereminder.feature.appointment_screen.presentation.appointments_main

import com.example.medicinereminder.data.model.DoctorWithAppointment

sealed interface AppointmentsAction {
    data class UpdateTab(val tab: AppointmentsTab): AppointmentsAction

    data class OpenBottomSheet(
        val bottomSheet: AppointmentBottomSheet,
        val reminder: DoctorWithAppointment?
    ) : AppointmentsAction

    data object CloseBottomSheet : AppointmentsAction
}