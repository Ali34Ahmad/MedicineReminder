package com.example.medicinereminder.data.repositories.impl

import com.example.medicinereminder.data.local.dao.MedicineDao
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.model.MedicineWithReminder
import com.example.medicinereminder.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val medicineTable: MedicineDao,
): MedicineRepository {

    override suspend fun addNewMedicine(medicine: Medicine) {
        medicineTable.addNewMedicine(medicine)
    }

    override suspend fun updateMedicine(medicine: Medicine) {
        medicineTable.updateMedicine(medicine)
    }

    override fun getMedicinesWithReminders(): Flow<List<MedicineWithReminder>> =
        medicineTable.getMedicinesWithReminders()

    override fun getTodayMedicinesWithReminders(): Flow<List<MedicineWithReminder>> =
        medicineTable.getTodayMedicinesWithReminders()
}