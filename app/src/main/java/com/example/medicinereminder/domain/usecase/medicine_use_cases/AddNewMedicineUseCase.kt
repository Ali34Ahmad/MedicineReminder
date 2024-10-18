package com.example.medicinereminder.domain.usecase.medicine_use_cases

import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.domain.repository.MedicineRepository
import javax.inject.Inject

class AddNewMedicineUseCase @Inject constructor(
    private val medicineRepository: MedicineRepository
) {
    suspend operator fun invoke(medicine: Medicine){
        medicineRepository.addNewMedicine(medicine)
    }
}