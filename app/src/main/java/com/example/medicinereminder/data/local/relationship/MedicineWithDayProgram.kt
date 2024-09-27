package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.DayProgram
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.utilities.RoomConstants

data class MedicineWithDays(
    @Embedded val medicine: Medicine,
    @Relation(
        entity = DayProgram::class,
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.DayProgram.MEDICINE_ID
    )
    val dayProgram: List<DayProgram>
)