package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.medicinereminder.data.local.entity.MedicineReminder
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineReminderDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMedicineReminder(medicineReminder: MedicineReminder)

    @Update
    suspend fun updateMedicineReminder(medicineReminder: MedicineReminder)

    @Delete
    fun deleteMedicineReminder(medicineReminder: MedicineReminder)

    @Query("""
        SELECT *
        FROM medicine_reminder
    """)
    fun getAllMedicineReminders(): Flow<List<MedicineReminder>>
}