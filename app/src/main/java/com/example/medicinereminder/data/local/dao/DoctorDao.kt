package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.utilities.RoomConstants
import kotlinx.coroutines.flow.Flow

@Dao
interface DoctorDao {

    @Upsert
    suspend fun upsertDoctor(doctor: Doctor)

    @Delete
    suspend fun deleteDoctor(doctor: Doctor)

    @Query("""
        SELECT *
        FROM doctor
    """)
    fun getAllDoctors() : Flow<List<Doctor>>

}