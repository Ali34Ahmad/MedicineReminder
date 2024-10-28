package com.example.medicinereminder.domain.usecase.medicine_use_cases

import com.example.medicinereminder.data.model.MedicineWithReminder
import com.example.medicinereminder.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMedicinesWithReminders @Inject constructor(
    private val medicineRepository: MedicineRepository
) {
    suspend operator fun invoke(): Flow<List<MedicineWithReminder>>  =
        medicineRepository.getMedicinesWithReminders()

}