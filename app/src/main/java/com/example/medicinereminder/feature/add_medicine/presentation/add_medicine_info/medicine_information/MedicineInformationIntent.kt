package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_information

sealed interface MedicineInformationIntent {
    data class UpdateMedicineName(val name: String) : MedicineInformationIntent
    data class UpdateQuantity(val quantity: String) : MedicineInformationIntent
    data class UpdateCompanyName(val companyName: String) : MedicineInformationIntent
    data object ValidateData : MedicineInformationIntent
}