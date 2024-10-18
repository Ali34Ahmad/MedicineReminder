package com.example.medicinereminder.domain.usecase.medicine_reminder_use_cases

import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.domain.repository.MedicineReminderRepository
import javax.inject.Inject

class UpdateMedicineReminderUseCase @Inject constructor(
    private val medicineReminderRepository: MedicineReminderRepository
) {
    suspend operator fun invoke(medicineReminder: MedicineReminder) {
        medicineReminderRepository.updateMedicineReminder(medicineReminder)
    }
}