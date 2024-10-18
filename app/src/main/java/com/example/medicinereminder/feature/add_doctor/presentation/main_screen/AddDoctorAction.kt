package com.example.medicinereminder.feature.add_doctor.presentation.main_screen

import com.example.medicinereminder.data.local.entity.Doctor

sealed interface AddDoctorAction {
    data class UpdateDoctorName(val name: String) : AddDoctorAction
    data class UpdateSpecialty(val specialty: String) : AddDoctorAction
    data class UpdatePhoneNumber(val  phoneNumber: String) : AddDoctorAction
    data class UpdateStateOrGovernment(val stateOrGovernment: String): AddDoctorAction
    data class UpdateCity(val city: String) : AddDoctorAction
    data class UpdateStreet(val street: String) : AddDoctorAction
    data class UpdateNote(val note: String): AddDoctorAction
    data object Confirm: AddDoctorAction
    data object Cancel: AddDoctorAction
    data object NavigateUp: AddDoctorAction
    data object ShowExistingDoctorsMenu: AddDoctorAction
    data object HideExistingDoctorsMenu: AddDoctorAction
    data object ShowContactInfo: AddDoctorAction
    data object HideContactInfo: AddDoctorAction
    data object ShowAddressInfo: AddDoctorAction
    data object HideAddressInfo: AddDoctorAction
    data object ShowBottomSheet: AddDoctorAction
    data object HideBottomSheet: AddDoctorAction
    data class AddExistingDoctor(val doctor: Doctor): AddDoctorAction
}