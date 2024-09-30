package com.example.medicinereminder.common.model

import com.example.medicinereminder.common.enums.AppointmentState

data class AppointmentTableItemInfo(
    val date: String,
    val time: String,
    val modifiedAt: String,
    val state: AppointmentState
)