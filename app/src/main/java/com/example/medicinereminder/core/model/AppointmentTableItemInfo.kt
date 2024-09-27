package com.example.medicinereminder.core.model

import com.example.medicinereminder.core.enums.AppointmentState

data class AppointmentTableItemInfo(
    val date: String,
    val time: String,
    val modifiedAt: String,
    val state: AppointmentState
)