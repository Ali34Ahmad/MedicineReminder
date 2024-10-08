package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.utilities.RoomConstants

data class MedicineWithDoctor(
    @Embedded val medicine: Medicine,
    @Relation(
        entity = Doctor::class,
        parentColumn = RoomConstants.Medicine.DOCTOR_ID,
        entityColumn = RoomConstants.Doctor.ID
    )
    val doctor: Doctor?
)