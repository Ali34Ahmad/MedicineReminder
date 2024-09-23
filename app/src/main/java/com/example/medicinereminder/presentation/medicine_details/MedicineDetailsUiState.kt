package com.example.medicinereminder.presentation.medicine_details

import com.example.medicinereminder.data.local.relationship.MedicineWithAllDetails

data class MedicineDetailsUiState (
    val medicineWithAllDetails: MedicineWithAllDetails?=null
)