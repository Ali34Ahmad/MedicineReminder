package com.example.medicinereminder.feature.add_appointment.presenation

import com.example.medicinereminder.data.local.entity.Doctor
import java.time.LocalDate
import java.time.LocalTime

data class AddAppointmentUIState(
    val isDetailsShown: Boolean = false,
    val selectedDate: LocalDate? = null,
    val isCalenderShown: Boolean = false,
    val selectedTime: LocalTime? = null,
    val isConfirmButtonEnabled: Boolean = false,
    val isDialogBoxShown: Boolean = false,
    val doctor: Doctor = Doctor(name = "no name", specialty = "not specified")
)