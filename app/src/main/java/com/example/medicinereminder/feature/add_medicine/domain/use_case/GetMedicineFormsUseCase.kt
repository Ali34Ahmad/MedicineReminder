package com.example.medicinereminder.feature.add_medicine.domain.use_case

import com.example.medicinereminder.data.local.entity.MedicineForm
import com.example.medicinereminder.feature.add_medicine.domain.repository.MedicineFormRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMedicineFormsUseCase @Inject constructor(
    private val medicineFormRepository: MedicineFormRepository
) {
    operator fun invoke(): Flow<List<MedicineForm>> =
        medicineFormRepository.getMedicineForms()
}