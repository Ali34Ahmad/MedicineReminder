package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.AlternativeMedicine
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.utilities.RoomConstants

data class AlternativeMedicineWithMedicine(
    @Embedded val alternativeMedicine: AlternativeMedicine,
    @Relation(
        parentColumn = RoomConstants.AlternativeMedicine.ALTERNATIVE_MEDICINE_ID,
        entityColumn = RoomConstants.Medicine.ID
    )
    val medicine: Medicine
)