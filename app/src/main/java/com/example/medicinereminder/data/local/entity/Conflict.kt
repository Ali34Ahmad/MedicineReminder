package com.example.medicinereminder.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.medicinereminder.utilities.RoomConstants

@Entity(
    tableName = RoomConstants.Conflict.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Medicine::class,
            parentColumns = [RoomConstants.Medicine.ID],
            childColumns = [RoomConstants.Conflict.MEDICINE_ID],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(
            value = [
                RoomConstants.Conflict.MEDICINE_ID
            ]
        ),
    ]
)
data class Conflict(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = RoomConstants.Conflict.DESCRIPTION)val description: String,
    @ColumnInfo(name = RoomConstants.Conflict.MEDICINE_ID) val medicineId: Int,
)
