package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.AlternativeMedicine
import com.example.medicinereminder.data.local.entity.Conflict
import com.example.medicinereminder.data.local.entity.DayProgram
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.PharmaceuticalForm
import com.example.medicinereminder.data.local.entity.Time
import com.example.medicinereminder.utilities.RoomConstants

data class MedicineWithAllDetails(
    @Embedded
    val medicine: Medicine,
    @Relation(
        entity = PharmaceuticalForm::class,
        parentColumn = RoomConstants.Medicine.PHARMACEUTICAL_FORM_ID,
        entityColumn = RoomConstants.PharmaceuticalForm.ID,
    )
    val pharmaceuticalForm: PharmaceuticalForm,
    @Relation(
        entity = Doctor::class,
        parentColumn = RoomConstants.Medicine.DOCTOR_ID,
        entityColumn = RoomConstants.Doctor.ID,
    )
    val doctor: Doctor,
    @Relation(
        entity = Conflict::class,
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.Conflict.MEDICINE_ID,
    )
    val conflicts:List<Conflict>,
    @Relation(
        entity = AlternativeMedicine::class,
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.AlternativeMedicine.ORIGINAL_MEDICINE_ID
    )
    val alternativeMedicine:List<AlternativeMedicine>,
    @Relation(
        entity = DayProgram::class,
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.DayProgram.MEDICINE_ID
    )
    val dayProgram: List<DayProgram>,
    @Relation(
        entity = Time::class,
        parentColumn = RoomConstants.DayProgram.ID,
        entityColumn = RoomConstants.Time.DAY_PROGRAM_ID
    )
    val times: List<Time>
)
