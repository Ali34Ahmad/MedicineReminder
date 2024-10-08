package com.example.medicinereminder.feature.add_medicine.domain.use_case

import com.example.medicinereminder.R
import javax.inject.Inject

class ValidateInteractionUseCase @Inject constructor(){
    operator fun invoke(interaction: String): ValidationResult {
        if (interaction.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.interaction_cannot_be_blank
            )
        }
        val containsCharacters=interaction.any { it.isLetter() }
        if (!containsCharacters) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.interaction_must_contain_letter
            )
        }
        return ValidationResult(successful = true)
    }
}