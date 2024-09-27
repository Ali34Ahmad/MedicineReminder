package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.medicinereminder.data.local.entity.PharmaceuticalForm
import kotlinx.coroutines.flow.Flow

@Dao
interface PharmaceuticalFormDao {
    @Upsert
    suspend fun upsertForm(form: PharmaceuticalForm)

    @Delete
    suspend fun deleteForm(form: PharmaceuticalForm)

    @Query("""
        SELECT *
        FROM pharmaceutical_form
    """)
    fun getAllForm(): Flow<List<PharmaceuticalForm>>
}