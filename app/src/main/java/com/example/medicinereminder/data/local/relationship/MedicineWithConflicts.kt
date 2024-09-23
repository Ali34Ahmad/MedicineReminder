package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.Conflict
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.utilities.RoomConstants

data class MedicineWithConflicts(
    @Embedded val medicine: Medicine,
    @Relation(
        entity= Conflict::class,
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.Conflict.MEDICINE_ID
    )
    val conflicts: List<Conflict>
)
