package com.example.medicinereminder.data.repositories

import com.example.medicinereminder.data.local.entity.Appointment
import kotlinx.coroutines.flow.Flow

interface AppointmentRepository {

    fun deleteAppointment(appointment: Appointment)

    suspend fun updateAppointment(appointment: Appointment)

    suspend fun insertAppointment(appointment: Appointment)

    val appointments: Flow<List<Appointment>>

}