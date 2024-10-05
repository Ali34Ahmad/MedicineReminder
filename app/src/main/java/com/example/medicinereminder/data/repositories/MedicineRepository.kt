package com.example.medicinereminder.data.repositories

import com.example.medicinereminder.data.local.relationship.MedicineReminderInfo
import kotlinx.coroutines.flow.Flow


interface MedicineRepository {
    val todayMedicineReminders: Flow<List<MedicineReminderInfo>>
}