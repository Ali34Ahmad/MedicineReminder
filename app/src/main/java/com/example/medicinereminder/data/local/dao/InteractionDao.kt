package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.medicinereminder.data.local.entity.Interaction

@Dao
interface InteractionDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertInteraction(vararg interaction: Interaction)

    @Delete
    suspend fun deleteInteraction(interaction: Interaction)

    @Update
    suspend fun updateInteraction(interaction: Interaction)
}