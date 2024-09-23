package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.AlternativeMedicine
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.utilities.RoomConstants

data class MedicineWithAlternativeMedicines(
    @Embedded
    val medicine: Medicine,
    @Relation(
        entity = AlternativeMedicine::class,
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.AlternativeMedicine.ORIGINAL_MEDICINE_ID
    )
    val alternativeMedicines: List<Medicine>
)
