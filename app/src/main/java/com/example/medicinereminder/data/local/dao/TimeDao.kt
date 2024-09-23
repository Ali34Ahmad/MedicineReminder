package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.medicinereminder.data.local.entity.Time

@Dao
interface TimeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addTime(vararg time: Time)

    @Delete
    suspend fun deleteTime(vararg time: Time)

    @Update
    suspend fun updateTime(vararg time: Time)
}