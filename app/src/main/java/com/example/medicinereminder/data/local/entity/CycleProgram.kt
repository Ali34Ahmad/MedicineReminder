package com.example.medicinereminder.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.medicinereminder.data.enums.ProgramType
import com.example.medicinereminder.data.local.utilities.RoomConstants

@Entity(
    tableName = RoomConstants.CycleProgram.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Medicine::class,
            parentColumns = [RoomConstants.Medicine.ID],
            childColumns = [RoomConstants.CycleProgram.MEDICINE_ID],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(
            value = [
                RoomConstants.CycleProgram.MEDICINE_ID
            ]
        ),
    ]
)
data class CycleProgram(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = RoomConstants.CycleProgram.MEDICINE_ID) val medicineId: Int,
    @ColumnInfo(name = RoomConstants.CycleProgram.PROGRAM_TYPE)val programType: ProgramType,
    @ColumnInfo(name = RoomConstants.CycleProgram.DOSE_DURATION_BY_DAYS)val doseDurationByDays:Int,
    @ColumnInfo(name = RoomConstants.CycleProgram.BREAK_DURATION_BY_DAYS)val breakDurationByDays:Int,
    val numberOfDosesPerDay:Int,
)
