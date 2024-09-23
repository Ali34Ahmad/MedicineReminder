package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.medicinereminder.data.local.entity.AlternativeMedicine
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.PharmaceuticalForm
import com.example.medicinereminder.data.local.relationship.MedicineWithAllDetails
import com.example.medicinereminder.data.local.relationship.MedicineWithAlternativeMedicines
import com.example.medicinereminder.data.local.relationship.MedicineWithConflicts
import com.example.medicinereminder.data.local.relationship.MedicineWithDayProgramsAndTimes
import com.example.medicinereminder.data.local.relationship.MedicineWithDoctor
import com.example.medicinereminder.data.local.relationship.MedicineWithPharmaceuticalForm

typealias MedicineId = Long
typealias DoctorId = Long

@Dao
interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addNewMedicine(medicine: Medicine): MedicineId

    @Query("SELECT * FROM medicine WHERE id = :medicineId")
    suspend fun getMedicineById(medicineId: Long): Medicine

    @Transaction
    @Query("SELECT * FROM medicine")
    suspend fun getAllMedicinesWithDetails(): List<MedicineWithAllDetails>

    @Transaction
    @Query("SELECT * FROM medicine WHERE id=:medicineId")
    suspend fun getMedicineWithDetailsById(medicineId: Long): MedicineWithAllDetails


    @Transaction
    @Query("SELECT * FROM medicine WHERE id = :medicineId")
    suspend fun getMedicineWithConflicts(medicineId: Long): MedicineWithConflicts

    @Transaction
    @Query("SELECT * FROM medicine WHERE id = :medicineId")
    suspend fun getMedicineWithPharmaceuticalForm(medicineId: Long): MedicineWithPharmaceuticalForm

    @Transaction
    @Query("SELECT * FROM medicine WHERE id = :medicineId")
    suspend fun getMedicineWithDoctor(medicineId: Long): MedicineWithDoctor

    @Transaction
    @Query("SELECT * FROM medicine WHERE id =:medicineId")
    suspend fun getMedicineWithDayProgramsAndTimes(medicineId: Long): MedicineWithDayProgramsAndTimes

    @Transaction
    @Query("SELECT * FROM medicine WHERE id =:originalMedicineId")
    suspend fun getMedicineWithAlternativeMedicines(originalMedicineId: Long): MedicineWithAlternativeMedicines

    @Transaction
    suspend fun getMedicineFromAlternativeMedicines(originalMedicineId: Long):List<Medicine>{
        val medicineWithAlternativeMedicines:MedicineWithAlternativeMedicines = getMedicineWithAlternativeMedicines(originalMedicineId)
        val alternativeMedicinesList= mutableListOf<Medicine>()
        medicineWithAlternativeMedicines.alternativeMedicines.forEach {
            alternativeMedicinesList.add(
                getMedicineById(it.alternativeMedicineId.toLong())
            )
        }
        return alternativeMedicinesList
    }

    @Transaction
    suspend fun deleteAndReplace(originalMedicineId: Int) {
        val oldestAlternative = getOldestAlternative(originalMedicineId)
        if (oldestAlternative != null) {
            updateOriginalMedicineId(oldestAlternative.alternativeMedicineId, originalMedicineId)
        }
        deleteOriginalMedicine(originalMedicineId)
    }

    @Query("SELECT * FROM alternative_medicine WHERE original_medicine_id = :originalMedicineId ORDER BY replacement_date ASC LIMIT 1")
    suspend fun getOldestAlternative(originalMedicineId: Int): AlternativeMedicine?

    @Query("UPDATE alternative_medicine SET original_medicine_id = :newOriginalId WHERE original_medicine_id = :oldOriginalId")
    suspend fun updateOriginalMedicineId(newOriginalId: Int, oldOriginalId: Int)

    @Query("DELETE FROM medicine WHERE id = :originalMedicineId")
    suspend fun deleteOriginalMedicine(originalMedicineId: Int)

    @Update
    suspend fun updateMedicine(medicine: Medicine)
}