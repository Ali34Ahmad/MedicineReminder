package com.example.medicinereminder.common.model

import com.example.medicinereminder.common.enums.AppointmentState
import com.example.medicinereminder.data.enums.ReminderState

data class AppointmentTableItemInfo(
    val date: String,
    val time: String,
    val modifiedAt: String,
    val state: ReminderState,
    val selected:Boolean,
)