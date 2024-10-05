package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.local.relationship.DoctorWithAppointments
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

    @Transaction
    @Query("""
        SELECT * 
        FROM doctor
        WHERE id in (
            SELECT doctor_id
            FROM appointment
            WHERE date_time  BETWEEN
        strftime('%s', 'now', 'start of day') * 1000 AND
        strftime('%s', 'now', 'start of day', '+1 day') * 1000 - 1
        )
    """)
    fun getDailyAppointments(): Flow<List<DoctorWithAppointments>>

    @Transaction
    @Query("""
        SELECT * 
        FROM doctor
    """)
    fun getDoctorsWithAppointments(): Flow<List<DoctorWithAppointments>>

}