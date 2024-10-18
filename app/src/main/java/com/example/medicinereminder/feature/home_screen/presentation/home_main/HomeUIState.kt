package com.example.medicinereminder.feature.home_screen.presentation.home_main

import androidx.annotation.StringRes
import com.example.medicinereminder.R
import com.example.medicinereminder.data.model.TimedEvent


data class HomeUIState(
    val currentTab: HomeTab = HomeTab.ALL,
    val shownBottomSheet: HomeBottomSheet = HomeBottomSheet.ClOSE,
    val selectedReminder: TimedEvent? = null
)
enum class HomeTab(
    @StringRes val title: Int,
){
    ALL(R.string.all),
    MEDICINES(R.string.medicines),
    APPOINTMENTS(R.string.appointments),
    REFILL(R.string.refill)
}
enum class HomeBottomSheet{
    APPOINTMENT,
    REMINDER,
    ClOSE
}