package com.example.medicinereminder.presentation.ui.helper

import com.example.medicinereminder.common.model.AppointmentTableItemInfo
import com.example.medicinereminder.data.enums.ReminderState


val appointmentTableItems = listOf(
    AppointmentTableItemInfo(
        selected = false,
        date = "Sep 10, 2024",
        time = "12:00 PM",
        modifiedAt = "Sep 5, 2024",
        state = ReminderState.TAKEN,
        appointmentId = 0
    ),
    AppointmentTableItemInfo(
        selected = false,
        date = "Sep 10, 2024",
        time = "12:00 PM",
        modifiedAt = "Sep 5, 2024",
        state = ReminderState.TAKEN,
        appointmentId = 1
    ),
    AppointmentTableItemInfo(
        selected = false,
        date = "Sep 10, 2024",
        time = "12:00 PM",
        modifiedAt = "Sep 5, 2024",
        state = ReminderState.UPCOMING,
        appointmentId = 3
    ),
    AppointmentTableItemInfo(
        selected = false,
        date = "Sep 10, 2024",
        time = "12:00 PM",
        modifiedAt = "Sep 5, 2024",
        state = ReminderState.UPCOMING,
        appointmentId = 4
    ),
    AppointmentTableItemInfo(
        selected = false,
        date = "Sep 10, 2024",
        time = "12:00 PM",
        modifiedAt = "Sep 5, 2024",
        state = ReminderState.UPCOMING,
        appointmentId = 5
    ),
    )