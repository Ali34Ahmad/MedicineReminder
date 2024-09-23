package com.example.medicinereminder.presentation.ui.constants

import androidx.compose.material.icons.outlined.Home
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.model.BottomNavItem

object BottomNavItems {
    fun get()= listOf(
        BottomNavItem(
            label = R.string.upcoming,
            route = "Upcoming"
        ),
        BottomNavItem(
            label = R.string.medicines,
            route = "Medicines"
        ),
        BottomNavItem(
            label = R.string.appts,
            route = "Appointments"
        ),
        BottomNavItem(
            label = R.string.tracker,
            route = "Tracker"
        ),
    )
}