package com.example.medicinereminder.feature.appointment_screen

import androidx.annotation.StringRes
import com.example.medicinereminder.R
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.model.ReminderInfo

data class AppointmentsUiState(
    val isBottomSheetShown: Boolean = false,
    val currentTab: AppointmentsTab = AppointmentsTab.UPCOMING,
    val selectedReminder: ReminderInfo? = null
)

enum class AppointmentsTab(
    @StringRes val title: Int,
){
    UPCOMING(R.string.upcoming),
    COMPLETED(R.string.completed),
    MISSED(R.string.missed)
}