package com.example.medicinereminder.feature.add_medicine.data

import com.example.medicinereminder.data.local.dao.MedicineFormDao
import com.example.medicinereminder.data.local.entity.MedicineForm
import com.example.medicinereminder.feature.add_medicine.domain.repository.MedicineFormRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MedicineFormRepositoryImpl @Inject constructor(
    private val medicineFormDao: MedicineFormDao
):MedicineFormRepository {
    override fun getMedicineForms(): Flow<List<MedicineForm>> {
        return medicineFormDao.getAllForm()
    }

    override suspend fun addMedicineForm(medicineForm: MedicineForm) {
        medicineFormDao.insertForm(medicineForm)
    }

    override suspend fun deleteMedicineForm(medicineForm: MedicineForm) {
        medicineFormDao.deleteForm(medicineForm)
    }
}