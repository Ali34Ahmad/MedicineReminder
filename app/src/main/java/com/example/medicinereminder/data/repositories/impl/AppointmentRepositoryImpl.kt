package com.example.medicinereminder.data.repositories.impl

import com.example.medicinereminder.data.local.dao.AppointmentDao
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.domain.repository.AppointmentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    private val appointmentTable: AppointmentDao
): AppointmentRepository {
    override suspend fun addNewAppointment(appointment: Appointment) {
        appointmentTable.addNewAppointment(appointment)
    }

    override suspend fun updateAppointment(appointment: Appointment) {
        appointmentTable.updateAppointment(appointment)
    }

    override fun deleteAppointment(appointment: Appointment) {
        appointmentTable.deleteAppointment(appointment)
    }

    override fun getAppointmentsByDoctorId(id: Int): Flow<List<Appointment>> =
        appointmentTable.getAppointmentsByDoctorId(id)

}