package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_form

import com.example.medicinereminder.data.local.entity.MedicineForm
import com.example.medicinereminder.data.local.medicineFormsFakes

data class MedicineFormUiState(
    val medicineForms: List<MedicineForm> = emptyList(),
    val showDeleteMedicineFormConfirmationDialog: Boolean=false,
    val pharmaceuticalFormIsExpanded: Boolean=false,
    val selectedPharmaceuticalFormIndex: Int?=null,
    val medicineFormText: String="",
    val medicineFormErrorMessage: Int?=null,
    val showAddMedicineFormDialog: Boolean=false,
    val medicineFormToDeleteIndex: Int?=null,
)
