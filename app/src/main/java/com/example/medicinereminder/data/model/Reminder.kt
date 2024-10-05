package com.example.medicinereminder.data.model

import com.example.medicinereminder.data.enums.ReminderState

interface Reminder {
    val dateTime: Long
    val id: Int
    val reminderState: ReminderState
}