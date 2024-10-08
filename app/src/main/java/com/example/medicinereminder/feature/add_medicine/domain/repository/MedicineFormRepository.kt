package com.example.medicinereminder.feature.add_medicine.domain.repository

import com.example.medicinereminder.data.local.entity.MedicineForm
import kotlinx.coroutines.flow.Flow

interface MedicineFormRepository {
    fun getMedicineForms(): Flow<List<MedicineForm>>
    suspend fun addMedicineForm(medicineForm: MedicineForm)
    suspend fun deleteMedicineForm(medicineForm: MedicineForm)
}