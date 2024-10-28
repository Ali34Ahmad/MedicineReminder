package com.example.medicinereminder.feature.appointment_screen.presentation.appointments_main

import androidx.annotation.StringRes
import com.example.medicinereminder.R
import com.example.medicinereminder.data.model.DoctorWithAppointment

data class AppointmentsUiState(
    val shownBottomSheet: AppointmentBottomSheet = AppointmentBottomSheet.CLOSE,
    val currentTab: AppointmentsTab = AppointmentsTab.UPCOMING,
    val selectedReminder: DoctorWithAppointment? = null
)

enum class AppointmentsTab(
    @StringRes val title: Int,
){
    UPCOMING(R.string.upcoming),
    COMPLETED(R.string.completed),
    MISSED(R.string.missed),
    STOPPED(R.string.stopped),
    UNSPECIFIED(R.string.unspecified)
}
enum class AppointmentBottomSheet{
    CLOSE,
    ADD_APPOINTMENT,
    APPOINTMENT_DETAILS
}