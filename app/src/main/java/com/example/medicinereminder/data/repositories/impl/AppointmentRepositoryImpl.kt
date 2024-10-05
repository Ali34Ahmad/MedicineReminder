package com.example.medicinereminder.data.repositories.impl

import com.example.medicinereminder.data.local.dao.AppointmentDao
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.repositories.AppointmentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    private val appointmentTable: AppointmentDao
): AppointmentRepository{
    override val appointments: Flow<List<Appointment>>
        get() = appointmentTable.getAllAppointments()

    override fun deleteAppointment(appointment: Appointment) =
        appointmentTable.deleteAppointment(appointment)

    override suspend fun updateAppointment(appointment: Appointment) =
        appointmentTable.updateAppointment(appointment)

    override suspend fun insertAppointment(appointment: Appointment) =
        appointmentTable.insertAppointment(appointment)

}