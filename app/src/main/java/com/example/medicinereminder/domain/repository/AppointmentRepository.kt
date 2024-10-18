package com.example.medicinereminder.domain.repository

import com.example.medicinereminder.data.local.entity.Appointment
import kotlinx.coroutines.flow.Flow

interface AppointmentRepository {
    suspend fun addNewAppointment(appointment: Appointment)
    suspend fun updateAppointment(appointment: Appointment)
    fun deleteAppointment(appointment: Appointment)
    fun getAppointmentsByDoctorId(id: Int): Flow<List<Appointment>>
}