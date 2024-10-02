package com.example.medicinereminder.data.repositories.impl

import androidx.room.Transaction
import com.example.medicinereminder.data.local.dao.MedicineDao
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.relationship.MedicineReminderInfo
import com.example.medicinereminder.data.repositories.MedicineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val medicineDao: MedicineDao,
): MedicineRepository{
    override suspend fun addNewMedicine(medicine: Medicine)  =
        medicineDao.addNewMedicine(medicine)

    override fun getMedicineById(medicineId: Int): Medicine =
        medicineDao.getMedicineById(medicineId.toLong())

    override fun getMedicineReminderInfo(medicineId: Int): Flow<MedicineReminderInfo> =
        medicineDao.getMedicineReminderInfo(medicineId)

    override fun getDailyMedicineIds(): Flow<List<Int>> =
        medicineDao.getDailyMedicineIds()

    override fun getDailyMedicineReminderInfo(): Flow<List<MedicineReminderInfo>> =
         getDailyMedicineIds().map { ids ->
            ids.map {
                getMedicineReminderInfo(it).first()
            }
        }
}