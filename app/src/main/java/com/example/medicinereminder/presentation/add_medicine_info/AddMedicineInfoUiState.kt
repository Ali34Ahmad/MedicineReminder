package com.example.medicinereminder.presentation.add_medicine_info

import com.example.medicinereminder.data.local.entity.Conflict
import com.example.medicinereminder.data.local.entity.PharmaceuticalForm

data class AddMedicineInfoUiState(
    val medicineName: String = "",
    val companyName: String = "",
    val quantity: String = "",
    val phoneNumberTextFieldIsShowing: Boolean = false,
    val conflictText: String = "",
    val conflictTextFieldIsShowing: Boolean = false,
    val conflict: List<Conflict> = emptyList(),
    val noteText: String = "",
    val noteTextFieldIsShowing: Boolean = false,
    val pharmaceuticalForms: List<PharmaceuticalForm> = emptyList(),
    val pharmaceuticalFormIsExpanded: Int?=null,
    val selectedPharmaceuticalFormIndex: PharmaceuticalForm,
)
