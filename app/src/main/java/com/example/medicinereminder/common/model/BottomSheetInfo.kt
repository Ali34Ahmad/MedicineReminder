package com.example.medicinereminder.common.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomSheetInfo(
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
    val onClick: () -> Unit
)