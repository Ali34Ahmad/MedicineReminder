package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.medicinereminder.data.local.entity.AlternativeMedicine

@Dao
interface AlternativeMedicinesDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAlternativeMedicine(alternativeMedicine: AlternativeMedicine)

    @Delete
    suspend fun deleteAlternativeMedicine(alternativeMedicine: AlternativeMedicine)

    @Update
    suspend fun updateAlternativeMedicine(alternativeMedicine: AlternativeMedicine)
}