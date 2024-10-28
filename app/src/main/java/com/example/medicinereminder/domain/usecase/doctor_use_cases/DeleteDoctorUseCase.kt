package com.example.medicinereminder.domain.usecase.doctor_use_cases

import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.domain.repository.DoctorRepository
import javax.inject.Inject

class DeleteDoctorUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
){
    operator fun invoke(doctor: Doctor) {
        doctorRepository.deleteDoctor(doctor)
    }
}