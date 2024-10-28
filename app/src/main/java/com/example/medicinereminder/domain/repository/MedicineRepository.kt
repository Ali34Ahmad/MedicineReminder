package com.example.medicinereminder.domain.repository

import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.model.MedicineWithReminder
import kotlinx.coroutines.flow.Flow


interface MedicineRepository {

    suspend fun addNewMedicine(medicine: Medicine)
    suspend fun updateMedicine(medicine: Medicine)
    fun getMedicinesWithReminders() : Flow<List<MedicineWithReminder>>
    fun getTodayMedicinesWithReminders() : Flow<List<MedicineWithReminder>>
}