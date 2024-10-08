package com.example.medicinereminder.feature.add_medicine.domain.use_case

import com.example.medicinereminder.R
import javax.inject.Inject

class ValidateMedicineFormNameUseCase @Inject constructor() {
    operator fun invoke(medicineFormName: String): ValidationResult {
        if (medicineFormName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.medicine_form_name_cannot_be_blank
            )
        }
        val containsCharacters=medicineFormName.any { it.isLetter() }
        if (!containsCharacters) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.medicine_form_name_must_contain_letter
            )
        }
        return ValidationResult(successful = true)
    }
}