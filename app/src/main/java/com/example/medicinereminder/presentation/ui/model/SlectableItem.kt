package com.example.medicinereminder.presentation.ui.model

import com.example.medicinereminder.data.local.entity.Conflict

data class SelectableItem(
    val value:Conflict,
    val selected:Boolean
)