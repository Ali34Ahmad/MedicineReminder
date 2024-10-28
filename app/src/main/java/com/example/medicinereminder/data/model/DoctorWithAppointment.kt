package com.example.medicinereminder.data.model

import androidx.room.Embedded
import com.example.medicinereminder.data.enums.ReminderType
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.entity.Doctor

data class DoctorWithAppointment(
    @Embedded
    val doctor: Doctor,
    @Embedded
    val appointment: Appointment
): TimedEvent{
    override val dateTime: Long
        get() = appointment.dateTime
    override val id: String
        get() = appointment.id.toString() + ReminderType.APPOINTMENT.name
}