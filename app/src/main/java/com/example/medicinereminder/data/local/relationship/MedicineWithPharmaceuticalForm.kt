package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.MedicineForm
import com.example.medicinereminder.data.local.utilities.RoomConstants

data class MedicineWithPharmaceuticalForm(
    @Embedded val medicine: Medicine,
    @Relation(
        entity = MedicineForm::class,
        parentColumn = RoomConstants.Medicine.MEDICINE_FORM_ID,
        entityColumn = RoomConstants.MedicineForm.ID
    )
    val medicineForm: MedicineForm
)
