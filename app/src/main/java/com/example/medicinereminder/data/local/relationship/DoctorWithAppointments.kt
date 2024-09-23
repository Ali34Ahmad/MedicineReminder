package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.utilities.RoomConstants

data class DoctorWithAppointments(
    @Embedded
    val doctor: Doctor,
    @Relation(
        entity = Appointment::class,
        parentColumn = RoomConstants.Doctor.ID,
        entityColumn = RoomConstants.Appointment.DOCTOR_ID
    )
    val list: List<Appointment>
)
