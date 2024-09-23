package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.PharmaceuticalForm
import com.example.medicinereminder.utilities.RoomConstants

data class MedicineWithPharmaceuticalForm(
    @Embedded val medicine: Medicine,
    @Relation(
        entity = PharmaceuticalForm::class,
        parentColumn = RoomConstants.Medicine.PHARMACEUTICAL_FORM_ID,
        entityColumn = RoomConstants.PharmaceuticalForm.ID
    )
    val pharmaceuticalForm: PharmaceuticalForm
)
