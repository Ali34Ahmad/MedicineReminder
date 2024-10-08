package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.medicinereminder.data.local.entity.Interaction

@Dao
interface ConflictDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addConflict(vararg interaction: Interaction)

    @Delete
    suspend fun deleteConflict(interaction: Interaction)

    @Update
    suspend fun updateConflict(interaction: Interaction)
}