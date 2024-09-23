package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.CycleProgram
import com.example.medicinereminder.data.local.entity.DayProgram
import com.example.medicinereminder.data.local.entity.Time
import com.example.medicinereminder.utilities.RoomConstants

data class CycleProgramWithTime(
    @Embedded
    val dayProgram: DayProgram,
    @Relation(
        entity = Time::class,
        parentColumn = RoomConstants.CycleProgram.ID,
        entityColumn = RoomConstants.Time.CYCLE_PROGRAM_ID,
    )
    val times:List<Time>
)
