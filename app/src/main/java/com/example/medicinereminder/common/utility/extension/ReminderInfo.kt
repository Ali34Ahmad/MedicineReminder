package com.example.medicinereminder.common.utility.extension

import com.example.medicinereminder.data.enums.ReminderType
import com.example.medicinereminder.data.local.relationship.DoctorWithAppointments
import com.example.medicinereminder.data.model.ReminderInfo

fun DoctorWithAppointments.toReminderInfoList() : List<ReminderInfo>{
    val reminders: MutableList<ReminderInfo> = mutableListOf()
    this.appointments.forEach { appointment ->
        reminders.add(ReminderInfo(
            reminder = appointment,
            doctor = this.doctor,
            pharmaceuticalForm = null,
            conflicts = emptyList(),
            medicine = null,
            type = ReminderType.APPOINTMENT
        ))
    }
    return reminders
}