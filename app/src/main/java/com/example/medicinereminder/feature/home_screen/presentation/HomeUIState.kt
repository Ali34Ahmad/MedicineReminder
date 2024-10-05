package com.example.medicinereminder.feature.home_screen.presentation

import androidx.annotation.StringRes
import com.example.medicinereminder.R
import com.example.medicinereminder.data.model.ReminderInfo


data class HomeUIState(
    val selectedReminder: ReminderInfo? = null,
    val isBottomSheetShown: Boolean= false,
    val currentTab: HomeTab = HomeTab.ALL,
)
enum class HomeTab(
    @StringRes val title: Int,
){
    ALL(R.string.all),
    MEDICINES(R.string.medicines),
    APPOINTMENTS(R.string.appointments),
    REFILL(R.string.refill)
}