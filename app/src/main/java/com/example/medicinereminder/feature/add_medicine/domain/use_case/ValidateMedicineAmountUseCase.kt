package com.example.medicinereminder.feature.add_medicine.domain.use_case

import com.example.medicinereminder.R
import javax.inject.Inject

class ValidateMedicineAmountUseCase @Inject constructor() {
    operator fun invoke(amount: String): ValidationResult {
        if (amount.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.amount_cannot_be_blank
            )
        }
        amount.removeSpaces().toIntOrNull() ?: return ValidationResult(
            successful = false,
            errorMessage = R.string.amount_must_be_positive_whole_number
        )
        return ValidationResult(successful = true)
    }

    private fun String.removeSpaces(): String {
        return this.replace(" ", "")
    }
}