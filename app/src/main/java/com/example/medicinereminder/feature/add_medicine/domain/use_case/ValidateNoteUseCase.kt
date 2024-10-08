package com.example.medicinereminder.feature.add_medicine.domain.use_case

import com.example.medicinereminder.R
import javax.inject.Inject

class ValidateNoteUseCase @Inject constructor() {
    operator fun invoke(note: String): ValidationResult {
        if (note.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.note_cannot_be_blank
            )
        }
        val containsCharacters=note.any { it.isLetter() }
        if (!containsCharacters) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.note_must_contain_letter
            )
        }
        return ValidationResult(successful = true)
    }
}