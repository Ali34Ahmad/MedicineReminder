package com.example.medicinereminder.feature.add_doctor.presentation.main_screen

import com.example.medicinereminder.data.model.Address

data class AddDoctorUIState(
    val doctorName: String = "",
    val specialty: String = "",
    val phoneNumber: String? = null,
    val address: Address = Address(),
    val note: String = "",
    val isContactInfoShown: Boolean = false,
    val isAddressInfoShown: Boolean = false,
    val isExistingDoctorsMenuShown: Boolean = false,
    val isBottomSheetShown: Boolean = false
)
