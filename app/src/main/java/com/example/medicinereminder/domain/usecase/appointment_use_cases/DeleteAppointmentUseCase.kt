package com.example.medicinereminder.domain.usecase.appointment_use_cases

import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.domain.repository.AppointmentRepository
import javax.inject.Inject

class DeleteAppointmentUseCase @Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {
    operator fun invoke(appointment: Appointment) {
        appointmentRepository.deleteAppointment(appointment)
    }
}