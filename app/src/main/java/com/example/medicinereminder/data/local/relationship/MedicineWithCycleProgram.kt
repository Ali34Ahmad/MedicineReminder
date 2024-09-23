package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.CycleProgram
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.utilities.RoomConstants

data class MedicineWithCycleProgram(
    @Embedded val medicine: Medicine,
    @Relation(
        entity = CycleProgram::class,
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.CycleProgram.MEDICINE_ID
    )
    val dayProgram: CycleProgram
)