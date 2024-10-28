package com.example.medicinereminder.common.model

import com.example.medicinereminder.data.enums.ReminderState

data class AppointmentTableItemInfo(
    val appointmentId: Int,
    val date: String,
    val time: String,
    val modifiedAt: String,
    val state: ReminderState,
    var selected:Boolean,
)