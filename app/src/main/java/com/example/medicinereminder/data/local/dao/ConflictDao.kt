package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.medicinereminder.data.local.entity.Conflict

@Dao
interface ConflictDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addConflict(vararg conflict: Conflict)

    @Delete
    suspend fun deleteConflict( conflict: Conflict)

    @Update
    suspend fun updateConflict(conflict: Conflict)
}