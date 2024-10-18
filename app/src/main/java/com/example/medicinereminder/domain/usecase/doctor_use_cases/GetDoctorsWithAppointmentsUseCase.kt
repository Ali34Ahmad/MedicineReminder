package com.example.medicinereminder.domain.usecase.doctor_use_cases

import com.example.medicinereminder.domain.repository.DoctorRepository
import javax.inject.Inject

class GetDoctorsWithAppointmentsUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    operator fun invoke() = doctorRepository.getAppointments()
}