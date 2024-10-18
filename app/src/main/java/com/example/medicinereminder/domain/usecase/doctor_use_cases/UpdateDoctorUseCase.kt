package com.example.medicinereminder.domain.usecase.doctor_use_cases

import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.domain.repository.DoctorRepository
import javax.inject.Inject

class UpdateDoctorUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(doctor: Doctor) {
        doctorRepository.updateDoctor(doctor)
    }
}