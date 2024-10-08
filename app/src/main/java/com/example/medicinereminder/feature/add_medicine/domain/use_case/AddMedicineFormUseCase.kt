package com.example.medicinereminder.feature.add_medicine.domain.use_case

import com.example.medicinereminder.data.local.entity.MedicineForm
import com.example.medicinereminder.feature.add_medicine.domain.repository.MedicineFormRepository
import javax.inject.Inject

class AddMedicineFormUseCase @Inject constructor(
    private val medicineFormRepository: MedicineFormRepository
){
    suspend operator fun invoke(medicineForm: MedicineForm){
        medicineFormRepository.addMedicineForm(medicineForm)
    }
}