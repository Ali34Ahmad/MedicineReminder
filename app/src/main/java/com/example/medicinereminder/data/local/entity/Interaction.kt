package com.example.medicinereminder.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.medicinereminder.data.local.utilities.RoomConstants
import kotlin.String

@Entity(
    tableName = RoomConstants.Interaction.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Medicine::class,
            parentColumns = [RoomConstants.Medicine.ID],
            childColumns = [RoomConstants.Interaction.MEDICINE_ID],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(
            value = [
                RoomConstants.Interaction.MEDICINE_ID
            ]
        ),
    ]
)
data class Interaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = RoomConstants.Interaction.DESCRIPTION)val description: String,
    @ColumnInfo(name = RoomConstants.Interaction.MEDICINE_ID) val medicineId: Int,
)
