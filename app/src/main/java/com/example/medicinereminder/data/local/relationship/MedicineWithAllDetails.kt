package com.example.medicinereminder.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.medicinereminder.data.local.entity.AlternativeMedicine
import com.example.medicinereminder.data.local.entity.Interaction
import com.example.medicinereminder.data.local.entity.DayProgram
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.MedicineForm
import com.example.medicinereminder.data.local.entity.Time
import com.example.medicinereminder.data.local.utilities.RoomConstants

data class MedicineWithAllDetails(
    @Embedded
    val medicine: Medicine,
    @Relation(
        entity = MedicineForm::class,
        parentColumn = RoomConstants.Medicine.MEDICINE_FORM_ID,
        entityColumn = RoomConstants.MedicineForm.ID,
    )
    val medicineForm: MedicineForm,
    @Relation(
        entity = Doctor::class,
        parentColumn = RoomConstants.Medicine.DOCTOR_ID,
        entityColumn = RoomConstants.Doctor.ID,
    )
    val doctor: Doctor,
    @Relation(
        entity = Interaction::class,
        parentColumn = RoomConstants.Medicine.ID,
        entityColumn = RoomConstants.Interaction.MEDICINE_ID,
    )
    val interactions:List<Interaction>,
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
