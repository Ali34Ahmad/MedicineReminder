package com.example.medicinereminder.data.repositories.impl

import com.example.medicinereminder.data.local.dao.MedicineDao
import com.example.medicinereminder.data.local.relationship.MedicineReminderInfo
import com.example.medicinereminder.data.repositories.MedicineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val medicineDao: MedicineDao,
): MedicineRepository{
    override val dailyMedicineReminders: Flow<List<MedicineReminderInfo>>
        get() = medicineDao.getDailyMedicineReminders()
}