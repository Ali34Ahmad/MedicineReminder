package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.DayProgram
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.Time
import com.example.medicinereminder.utilities.RoomConstants

data class MedicineWithDayProgramsAndTimes(
    @Embedded val medicine: Medicine,
    @Relation(
        entity = DayProgram::class,
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.DayProgram.MEDICINE_ID
    )
    val dayProgram: List<DayProgram>,
    @Relation(
        entity = Time::class,
        parentColumn = RoomConstants.DayProgram.ID,
        entityColumn = RoomConstants.Time.DAY_PROGRAM_ID
    )
    val times: List<Time>

)