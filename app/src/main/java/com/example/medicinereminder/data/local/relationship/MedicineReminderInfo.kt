package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.Conflict
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.local.entity.PharmaceuticalForm
import com.example.medicinereminder.utilities.RoomConstants

data class MedicineReminderInfo(
    @Embedded val medicine: Medicine,
    @Relation(
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.Conflict.MEDICINE_ID,
        )
    val conflicts: List<Conflict>,
    @Relation(
        parentColumn = RoomConstants.Medicine.PHARMACEUTICAL_FORM_ID,
        entityColumn = RoomConstants.PharmaceuticalForm.ID,
    )
    val pharmaceuticalForm: PharmaceuticalForm,
    @Relation(
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.MedicineReminder.MEDICINE_ID,
    )
    val reminders: List<MedicineReminder>,
)