package com.example.medicinereminder.domain.repository

import com.example.medicinereminder.data.local.entity.MedicineReminder

interface MedicineReminderRepository {

    suspend fun addNewMedicineReminder(medicineReminder: MedicineReminder)

    suspend fun updateMedicineReminder(medicineReminder: MedicineReminder)

    fun deleteMedicineReminder(medicineReminder: MedicineReminder)

}