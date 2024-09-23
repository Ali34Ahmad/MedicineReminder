package com.example.medicinereminder.data.local.entity

import android.provider.SyncStateContract.Constants
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.medicinereminder.utilities.RoomConstants
import java.time.DayOfWeek

@Entity(
    tableName = RoomConstants.DayProgram.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Medicine::class,
            parentColumns = [RoomConstants.Medicine.ID],
            childColumns = [RoomConstants.DayProgram.MEDICINE_ID],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(
            value = [
                RoomConstants.DayProgram.MEDICINE_ID
            ]
        ),
    ]
)
data class DayProgram(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = RoomConstants.DayProgram.MEDICINE_ID) val medicineId: Int,
    val dayOfWeek: DayOfWeek,
    val numberOfDosesPerDay:Int,
)
