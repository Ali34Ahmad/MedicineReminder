package com.example.medicinereminder.feature.homeScreen.presentation

import androidx.annotation.StringRes
import com.example.medicinereminder.R


enum class HomeTab(
    @StringRes val title: Int,
){
    ALL(R.string.all),
    MEDICINES(R.string.medicines),
    APPOINTMENTS(R.string.appointments),
    REFILL(R.string.refill)
}