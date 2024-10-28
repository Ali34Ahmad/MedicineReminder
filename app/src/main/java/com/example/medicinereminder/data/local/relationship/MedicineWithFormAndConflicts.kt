package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.Interaction
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.MedicineForm
import com.example.medicinereminder.data.local.utilities.RoomConstants

data class MedicineWithFormAndConflicts(
    @Embedded val medicine: Medicine,
    @Embedded
    val form: MedicineForm,
    @Relation(
        parentColumn = RoomConstants.Medicine.ID, // Column in Medicine table
        entityColumn = RoomConstants.Interaction.MEDICINE_ID  // Column in Interaction table
    )
    val interactions: List<Interaction>
)