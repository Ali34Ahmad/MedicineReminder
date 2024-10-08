package com.example.medicinereminder.feature.add_medicine.domain.use_case

import com.example.medicinereminder.R
import javax.inject.Inject

class ValidateMedicineNameUseCase @Inject constructor(){
    operator fun invoke(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.medicine_name_cannot_be_blank
            )
        }
        val containsCharacters=name.any { it.isLetter() }
        if (!containsCharacters) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.medicine_name_must_contain_letter
            )
        }
        return ValidationResult(successful = true)
    }
}