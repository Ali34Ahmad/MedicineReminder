package com.example.medicinereminder.feature.doctor_details.presentation

import com.example.medicinereminder.feature.appointment_screen.presentation.appointments_main.AppointmentsTab

data class DoctorDetailsUIState(
    val selectedTab: AppointmentsTab = AppointmentsTab.UPCOMING,
    val isMarkAsButtonShown:Boolean = false,
    val isOptionsMenuShown: Boolean = false,
    val isMarkAsMenuShown: Boolean = false,
    val topAppBarState: TopAppBarState = TopAppBarState.INFO,
    val isEditButtonShown: Boolean = false,
    val isExtraDetailsMenuOpen:Boolean = false,
    val showingDialogBox: DoctorDetailsDialogBox = DoctorDetailsDialogBox.NONE
)
enum class TopAppBarState{
    INFO,
    ACTIONS
}
enum class DoctorDetailsDialogBox{
    NONE,
    DELETE_DOCTOR,
    DELETE_REMINDERS,
    STOP_REMINDERS
}