package com.example.medicinereminder.presentation.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class MoreOptionMenuItem(
    @DrawableRes val icon:Int,
    @StringRes val titleStringId:Int,
    val hasSubMenu:Boolean=false,
    val tint:Color= Color.Black,
    val textColor: Color=Color.Black,
)
