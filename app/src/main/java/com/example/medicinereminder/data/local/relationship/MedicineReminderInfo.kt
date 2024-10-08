package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.Interaction
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.local.entity.MedicineForm
import com.example.medicinereminder.data.local.utilities.RoomConstants

data class MedicineReminderInfo(
    @Embedded val medicine: Medicine,
    @Relation(
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.Interaction.MEDICINE_ID,
        )
    val interactions: List<Interaction>,
    @Relation(
        parentColumn = RoomConstants.Medicine.MEDICINE_FORM_ID,
        entityColumn = RoomConstants.MedicineForm.ID,
    )
    val medicineForm: MedicineForm,
    @Relation(
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.MedicineReminder.MEDICINE_ID,
    )
    val reminders: List<MedicineReminder>,
)