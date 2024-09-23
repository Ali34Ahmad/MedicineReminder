package com.example.medicinereminder.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.medicinereminder.utilities.RoomConstants

@Entity(
    tableName = RoomConstants.Time.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = DayProgram::class,
            parentColumns = [RoomConstants.DayProgram.ID],
            childColumns = [RoomConstants.Time.DAY_PROGRAM_ID],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DayProgram::class,
            parentColumns = [RoomConstants.CycleProgram.ID],
            childColumns = [RoomConstants.Time.CYCLE_PROGRAM_ID],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(
            value = [
                RoomConstants.Time.DAY_PROGRAM_ID
            ]
        ),
        Index(
            value = [
                RoomConstants.Time.CYCLE_PROGRAM_ID
            ]
        ),
    ]
)
data class Time(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val time: Long,
    @ColumnInfo(name = RoomConstants.Time.DAY_PROGRAM_ID) val dayProgramId: Int?,
    @ColumnInfo(name = RoomConstants.Time.CYCLE_PROGRAM_ID) val cycleProgramId: Int?,
    @ColumnInfo(name = RoomConstants.Time.DOSE_AMOUNT) val doseAmount: Int,
    @ColumnInfo(name = RoomConstants.Time.DATE_ADDED) val dateAdded: Long,
    @ColumnInfo(name = RoomConstants.Time.LAST_MODIFIED_DATE) val lastModifiedDate: Long,
)
