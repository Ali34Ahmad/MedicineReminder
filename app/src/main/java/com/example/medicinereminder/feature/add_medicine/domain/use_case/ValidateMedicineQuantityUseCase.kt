package com.example.medicinereminder.feature.add_medicine.domain.use_case

import com.example.medicinereminder.R
import javax.inject.Inject

class ValidateMedicineQuantityUseCase @Inject constructor() {
    operator fun invoke(quantity: String): ValidationResult {
        if (quantity.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.quantity_cannot_be_blank
            )
        }
        val quantityAsInt: Int = quantity.removeSpaces().toIntOrNull() ?: return ValidationResult(
            successful = false,
            errorMessage = R.string.quantity_must_be_positive_whole_number
        )
        return ValidationResult(successful = true)
    }

    private fun String.removeSpaces(): String {
        return this.replace(" ", "")
    }
}