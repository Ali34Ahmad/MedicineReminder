package com.example.medicinereminder.data.repositories.impl

import androidx.room.Query
import com.example.medicinereminder.data.local.dao.DoctorDao
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.model.DoctorWithAppointment
import com.example.medicinereminder.domain.repository.DoctorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val doctorTable: DoctorDao,
): DoctorRepository {
    override fun getAllDoctors(): Flow<List<Doctor>> =
        doctorTable.getAllDoctors()

    override fun getTodayAppointments(): Flow<List<DoctorWithAppointment>> =
        doctorTable.getTodayDoctorsWithAppointments()

    override fun getDoctorById(id: Int): Flow<Doctor> = doctorTable.getDoctorById(id)

    override fun getAppointments(): Flow<List<DoctorWithAppointment>> =
        doctorTable.getDoctorsWithAppointments()

    override suspend fun addNewDoctor(doctor: Doctor) {
        doctorTable.addNewDoctor(doctor)
    }

    override fun deleteDoctor(doctor: Doctor) {
        doctorTable.deleteDoctor(doctor)
    }

    override suspend fun updateDoctor(doctor: Doctor) {
        doctorTable.upsertDoctor(doctor)
    }


}