package com.example.medicinereminder.feature.add_medicine.domain.use_case

import com.example.medicinereminder.R
import javax.inject.Inject

class ValidateNumberOfDosesUseCase @Inject constructor() {
    operator fun invoke(numberOfDoses: String): ValidationResult {
        if (numberOfDoses.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.number_of_doses_cannot_be_blank
            )
        }
        numberOfDoses.removeSpaces().toIntOrNull() ?: return ValidationResult(
            successful = false,
            errorMessage = R.string.number_of_doses_must_be_positive_whole_number
        )
        return ValidationResult(successful = true)
    }

    private fun String.removeSpaces(): String {
        return this.replace(" ", "")
    }
}