package com.example.medicinereminder.data.repositories

import com.example.medicinereminder.data.local.entity.MedicineReminder

interface MedicineReminderRepository {

    suspend fun insertMedicineReminder(medicineReminder: MedicineReminder)

    suspend fun updateMedicineReminder(medicineReminder: MedicineReminder)

    fun deleteMedicineReminder(medicineReminder: MedicineReminder)

}