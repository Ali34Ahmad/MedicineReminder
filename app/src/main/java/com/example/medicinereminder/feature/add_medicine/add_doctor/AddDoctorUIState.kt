package com.example.medicinereminder.feature.add_medicine.add_doctor

import com.example.medicinereminder.data.model.Address

data class AddDoctorUIState(
    val doctorName: String = "",
    val specialty: String = "",
    val phoneNumber: String = "",
    val address: Address = Address(),
    val note: String = "",
)