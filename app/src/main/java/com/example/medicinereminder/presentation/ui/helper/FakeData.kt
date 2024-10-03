package com.example.medicinereminder.presentation.ui.helper

import com.example.medicinereminder.common.model.AppointmentTableItemInfo
import com.example.medicinereminder.data.enums.ReminderState


val appointmentTableItems = listOf(
    AppointmentTableItemInfo(
        selected = true,
        date = "Sep 10, 2024",
        time = "12:00 PM",
        modifiedAt = "Sep 5, 2024",
        state = ReminderState.TAKEN,
    ),
    AppointmentTableItemInfo(
        selected = true,
        date = "Sep 10, 2024",
        time = "12:00 PM",
        modifiedAt = "Sep 5, 2024",
        state = ReminderState.UNSPECIFIED,
    ),
    AppointmentTableItemInfo(
        selected = false,
        date = "Sep 10, 2024",
        time = "12:00 PM",
        modifiedAt = "Sep 5, 2024",
        state = ReminderState.STOPPED,
    ),
    AppointmentTableItemInfo(
        selected = true,
        date = "Sep 10, 2024",
        time = "12:00 PM",
        modifiedAt = "Sep 5, 2024",
        state = ReminderState.UNSPECIFIED,
    ),
    AppointmentTableItemInfo(
        selected = false,
        date = "Sep 10, 2024",
        time = "12:00 PM",
        modifiedAt = "Sep 5, 2024",
        state = ReminderState.UNSPECIFIED,
    ),
    AppointmentTableItemInfo(
        selected = false,
        date = "Sep 10, 2024",
        time = "12:00 PM",
        modifiedAt = "Sep 5, 2024",
        state = ReminderState.UNSPECIFIED,
    ),

    )