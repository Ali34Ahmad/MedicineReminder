package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.medicinereminder.data.local.entity.AlternativeMedicine
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.relationship.MedicineReminderInfo
import com.example.medicinereminder.data.local.relationship.MedicineWithAllDetails
import com.example.medicinereminder.data.local.relationship.MedicineWithAlternativeMedicines
import com.example.medicinereminder.data.local.relationship.MedicineWithConflicts
import com.example.medicinereminder.data.local.relationship.MedicineWithDayProgramsAndTimes
import com.example.medicinereminder.data.local.relationship.MedicineWithDoctor
import com.example.medicinereminder.data.local.relationship.MedicineWithPharmaceuticalForm
import com.example.medicinereminder.data.model.MedicineWithReminder
import kotlinx.coroutines.flow.Flow
import java.util.Collections.emptyList

typealias MedicineId = Long
typealias DoctorId = Long

@Dao
interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addNewMedicine(medicine: Medicine): MedicineId

    @Query("SELECT * FROM medicine WHERE id = :medicineId")
    fun getMedicineById(medicineId: Long): Medicine

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
        val alternativeMedicinesList:MutableList<Medicine> = emptyList()
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

    @Transaction
    @Query("SELECT * FROM medicine WHERE id = :medicineId")
    fun getMedicineReminderInfo(medicineId: Int) :Flow<MedicineReminderInfo>

    @Transaction
    @Query("""
        SELECT *
        FROM medicine
        INNER JOIN medicine_form ON medicine.medicine_form_id = medicine_form.medicine_from_id
        INNER JOIN medicine_reminder ON medicine.id = medicine_reminder.medicine_id
        ORDER BY medicine_reminder.date_time ASC
    """)
    fun getMedicinesWithReminders() : Flow<List<MedicineWithReminder>>

    @Transaction
    @Query("""
        SELECT *
        FROM medicine
        INNER JOIN medicine_form ON medicine.medicine_form_id = medicine_form.medicine_from_id
        INNER JOIN medicine_reminder ON medicine.id = medicine_reminder.medicine_id
        WHERE medicine_reminder.date_time  BETWEEN strftime('%s', 'now', 'start of day') * 1000 AND
        strftime('%s', 'now', 'start of day', '+1 day') * 1000 - 1
        ORDER BY medicine_reminder.date_time ASC
    """)
    fun getTodayMedicinesWithReminders(): Flow<List<MedicineWithReminder>>


}