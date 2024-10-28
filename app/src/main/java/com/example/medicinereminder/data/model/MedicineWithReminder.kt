package com.example.medicinereminder.data.model

import androidx.room.Embedded
import com.example.medicinereminder.data.enums.ReminderType
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.local.relationship.MedicineWithFormAndConflicts

data class MedicineWithReminder(
    @Embedded
    val medicineInfo: MedicineWithFormAndConflicts,
    @Embedded
    val reminder: MedicineReminder
): TimedEvent{
    override val dateTime: Long
        get() = reminder.dateTime
    override val id: String
        get() = reminder.id.toString() + ReminderType.MEDICINE.name
}
