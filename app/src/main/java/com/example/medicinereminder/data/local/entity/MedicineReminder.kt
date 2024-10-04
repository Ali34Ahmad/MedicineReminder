package com.example.medicinereminder.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.model.Reminder
import com.example.medicinereminder.utilities.RoomConstants

@Entity(
    tableName = RoomConstants.MedicineReminder.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Medicine::class,
            parentColumns = [RoomConstants.Medicine.ID],
            childColumns = [RoomConstants.MedicineReminder.MEDICINE_ID],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(
            value = [
                RoomConstants.MedicineReminder.MEDICINE_ID
            ]
        ),
    ]
)
data class MedicineReminder(
    @PrimaryKey(autoGenerate = true) override val id: Int = 0,
    @ColumnInfo(name = RoomConstants.MedicineReminder.MEDICINE_ID) val medicineId: Int,
    @ColumnInfo(name = RoomConstants.MedicineReminder.DATE_TIME) override val dateTime: Long,
    @ColumnInfo(name = RoomConstants.MedicineReminder.DOSE_AMOUNT) val doseAmount: Int,
    @ColumnInfo(name = RoomConstants.MedicineReminder.DATE_ADDED) val dateAdded: Long,
    @ColumnInfo(name = RoomConstants.MedicineReminder.IS_REFILL_REMINDER) val isRefillReminder: Boolean,
    val reminderState: ReminderState,
): Reminder
