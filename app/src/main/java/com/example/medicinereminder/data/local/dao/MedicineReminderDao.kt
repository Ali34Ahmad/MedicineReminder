package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.medicinereminder.data.local.entity.MedicineReminder
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineReminderDao {
    @Upsert
    suspend fun upsertMedicineReminder(medicineReminder: MedicineReminder)

    @Delete
    suspend fun deleteMedicineReminder(medicineReminder: MedicineReminder)

    @Query("""
        SELECT *
        FROM medicine_reminder
    """)
    fun getAllMedicineReminders(): Flow<List<MedicineReminder>>
}