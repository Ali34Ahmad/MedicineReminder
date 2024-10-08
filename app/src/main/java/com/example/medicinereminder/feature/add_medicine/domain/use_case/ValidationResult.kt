package com.example.medicinereminder.feature.add_medicine.domain.use_case

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: Int? = null
)
