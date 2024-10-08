package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.local.utilities.RoomConstants

data class MedicineWithMedicineReminder(
    @Embedded
    val medicine: Medicine,
    @Relation(
        entity = MedicineReminder::class,
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.MedicineReminder.MEDICINE_ID
    )
    val reminders:List<MedicineReminder>,
)