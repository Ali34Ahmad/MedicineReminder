package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.medicinereminder.data.local.entity.DayProgram

@Dao
interface DayProgramDao {
    //TODO("Verify that we don't pass the same day of week")
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addDayProgram(vararg dayProgram: DayProgram)

    @Delete
    suspend fun deleteDayProgram(vararg dayProgram: DayProgram)

    @Update
    suspend fun updateDayProgram(vararg dayProgram: DayProgram)
}