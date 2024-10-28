package com.example.medicinereminder.data.repositories.impl

import com.example.medicinereminder.data.local.dao.MedicineReminderDao
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.domain.repository.MedicineReminderRepository
import javax.inject.Inject

class MedicineReminderRepositoryImpl @Inject constructor(
    private val medicineReminderTable: MedicineReminderDao
): MedicineReminderRepository {
    override suspend fun addNewMedicineReminder(medicineReminder: MedicineReminder) {
        medicineReminderTable.insertMedicineReminder(medicineReminder)
    }

    override suspend fun updateMedicineReminder(medicineReminder: MedicineReminder) {
        medicineReminderTable.updateMedicineReminder(medicineReminder)
    }

    override fun deleteMedicineReminder(medicineReminder: MedicineReminder) {
        medicineReminderTable.deleteMedicineReminder(medicineReminder)
    }
}