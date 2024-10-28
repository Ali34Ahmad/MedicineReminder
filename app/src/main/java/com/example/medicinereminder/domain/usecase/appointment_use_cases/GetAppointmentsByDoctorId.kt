package com.example.medicinereminder.domain.usecase.appointment_use_cases

import com.example.medicinereminder.domain.repository.AppointmentRepository
import javax.inject.Inject

class GetAppointmentsByDoctorId @Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {
    operator fun invoke(doctorId: Int) =
        appointmentRepository.getAppointmentsByDoctorId(doctorId)
}