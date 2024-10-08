package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_information

import androidx.annotation.StringRes

data class MedicineInformationUiState(
    val medicineName: String = "",
    @StringRes val medicineNameErrorMessage: Int?=null,
    val companyName: String = "",
    val quantity: String = "",
    @StringRes val quantityErrorMessage: Int? = null,
    val validInput:Boolean=false,
)
