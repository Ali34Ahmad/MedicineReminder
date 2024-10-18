package com.example.medicinereminder.domain.usecase.medicine_use_cases

import com.example.medicinereminder.domain.repository.MedicineRepository
import javax.inject.Inject

class GetTodayMedicinesWithRemindersUseCase @Inject constructor(
    private val medicineRepository: MedicineRepository
) {
    operator fun invoke() = medicineRepository.getTodayMedicinesWithReminders()
}