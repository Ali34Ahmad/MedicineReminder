package com.example.medicinereminder.presentation.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

//TODO("Change route to type-safe navigation")
data class BottomNavItem(
    @StringRes val label:Int,
    val route:String,
)