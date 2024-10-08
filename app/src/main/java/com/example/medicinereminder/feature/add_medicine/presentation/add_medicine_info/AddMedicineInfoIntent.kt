package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info

sealed interface AddMedicineInfoIntent {
    data object ConfirmButtonClick : AddMedicineInfoIntent
}