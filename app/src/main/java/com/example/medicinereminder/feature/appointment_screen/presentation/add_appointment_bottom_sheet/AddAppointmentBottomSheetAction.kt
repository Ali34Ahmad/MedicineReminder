package com.example.medicinereminder.feature.appointment_screen.presentation.add_appointment_bottom_sheet

import com.example.medicinereminder.data.local.entity.Doctor

sealed interface AddAppointmentBottomSheetAction {
    data class ClickDetailsButton(val doctor: Doctor): AddAppointmentBottomSheetAction
    data class SelectDoctor(val doctor: Doctor): AddAppointmentBottomSheetAction
    data class UpdateQuery(val query: String): AddAppointmentBottomSheetAction
}