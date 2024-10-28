package com.example.medicinereminder.domain.repository

import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.model.DoctorWithAppointment
import kotlinx.coroutines.flow.Flow

interface DoctorRepository {
    fun getAllDoctors(): Flow<List<Doctor>>
    fun getAppointments(): Flow<List<DoctorWithAppointment>>
    fun getTodayAppointments(): Flow<List<DoctorWithAppointment>>
    fun getDoctorById(id: Int): Flow<Doctor>
    suspend fun addNewDoctor(doctor: Doctor)
    fun deleteDoctor(doctor: Doctor)
    suspend fun updateDoctor(doctor: Doctor)
}