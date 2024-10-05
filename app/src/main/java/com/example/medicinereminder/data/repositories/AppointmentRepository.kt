package com.example.medicinereminder.data.repositories

import com.example.medicinereminder.data.local.entity.Appointment

interface AppointmentRepository {

    fun deleteAppointment(appointment: Appointment)

    suspend fun updateAppointment(appointment: Appointment)

    suspend fun insertAppointment(appointment: Appointment)

}