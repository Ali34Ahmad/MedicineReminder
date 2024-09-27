package com.example.medicinereminder.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.utilities.RoomConstants

@Entity(
    tableName = RoomConstants.Appointment.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Doctor::class,
            parentColumns = [RoomConstants.Doctor.ID],
            childColumns = [RoomConstants.Appointment.DOCTOR_ID],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(
            value = [
                RoomConstants.Appointment.DOCTOR_ID
            ]
        ),
    ]
)
data class Appointment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = RoomConstants.Appointment.DOCTOR_ID) val doctorId: Int,
    @ColumnInfo(name = RoomConstants.Appointment.DATE_TIME) val dateTime: Long,
    val reminderState: ReminderState,
    @ColumnInfo(name = RoomConstants.Appointment.DATE_ADDED) val dateAdded: Long,
    @ColumnInfo(name = RoomConstants.Appointment.LAST_MODIFIED_DATE) val lastModifiedDate: Long,
    )
