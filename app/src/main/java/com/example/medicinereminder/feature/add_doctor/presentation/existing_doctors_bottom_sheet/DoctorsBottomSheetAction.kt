package com.example.medicinereminder.feature.add_doctor.presentation.existing_doctors_bottom_sheet

import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.feature.appointment_screen.presentation.add_appointment_bottom_sheet.AddAppointmentBottomSheetAction

sealed interface DoctorsBottomSheetAction {
    data class ClickDetailsButton(val doctor: Doctor): DoctorsBottomSheetAction
    data class SelectDoctor(val doctor: Doctor): DoctorsBottomSheetAction
    data class UpdateQuery(val query: String) : DoctorsBottomSheetAction
}