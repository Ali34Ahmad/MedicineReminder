package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.medicinereminder.data.local.entity.AlternativeMedicine

@Dao
interface MedicineDao {
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
}