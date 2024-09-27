package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.medicinereminder.data.local.entity.CycleProgram
import kotlinx.coroutines.flow.Flow

@Dao
interface CycleProgramDao {
    @Upsert
    suspend fun upsertCycleProgram(cycleProgram: CycleProgram)

    @Delete
    suspend fun deleteCycleProgram(cycleProgram: CycleProgram)

    @Query("""
        SELECT * 
    FROM cycle_program
    """)
    fun getAllCyclePrograms(): Flow<List<CycleProgram>>

}