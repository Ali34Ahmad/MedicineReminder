package com.example.medicinereminder.data.repositories

import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.local.relationship.DoctorWithAppointments
import kotlinx.coroutines.flow.Flow

interface DoctorRepository {
    val todayAppointments: Flow<List<DoctorWithAppointments>>
    val appointments: Flow<List<DoctorWithAppointments>>
    suspend fun addNewDoctor(doctor: Doctor)
}