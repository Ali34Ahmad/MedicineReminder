package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.medicinereminder.data.local.entity.MedicineForm
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineFormDao {
    @Upsert
    suspend fun insertForm(form: MedicineForm)

    @Delete
    suspend fun deleteForm(form: MedicineForm)

    @Query("""
        SELECT *
        FROM medicine_form
    """)
    fun getAllForm(): Flow<List<MedicineForm>>
}