package com.example.medicinereminder.data.repositories

import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.relationship.MedicineReminderInfo
import kotlinx.coroutines.flow.Flow


interface MedicineRepository {
    suspend fun addNewMedicine(medicine: Medicine) : Long
    fun getMedicineById(medicineId: Int): Medicine
    fun getMedicineReminderInfo(medicineId: Int): Flow<MedicineReminderInfo>
    fun getDailyMedicineIds() : Flow<List<Int>>
    fun getDailyMedicineReminderInfo() : Flow<List<MedicineReminderInfo>>
}