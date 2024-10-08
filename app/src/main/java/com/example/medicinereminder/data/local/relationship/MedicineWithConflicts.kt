package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.Interaction
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.utilities.RoomConstants

data class MedicineWithConflicts(
    @Embedded val medicine: Medicine,
    @Relation(
        entity= Interaction::class,
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.Interaction.MEDICINE_ID
    )
    val interactions: List<Interaction>
)
