package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.model.DoctorWithAppointment
import kotlinx.coroutines.flow.Flow

@Dao
interface DoctorDao {

    @Upsert
    suspend fun upsertDoctor(doctor: Doctor)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addNewDoctor(doctor: Doctor)

    @Update
    suspend fun updateDoctor(doctor: Doctor)

    @Delete
    fun deleteDoctor(doctor: Doctor)

    @Query("""
        SELECT *
        FROM doctor
    """)
    fun getAllDoctors() : Flow<List<Doctor>>

    @Query("SELECT * FROM doctor WHERE id = :id")
    fun getDoctorById(id: Int): Flow<Doctor>


    @Query("""
        SELECT * 
        FROM doctor
        INNER JOIN appointment ON doctor.id = appointment.doctor_id
        WHERE date_time  BETWEEN strftime('%s', 'now', 'start of day') * 1000 AND
        strftime('%s', 'now', 'start of day', '+1 day') * 1000 - 1
        ORDER BY appointment.date_time ASC
    """)
    fun getTodayDoctorsWithAppointments() : Flow<List<DoctorWithAppointment>>

    @Query("""
        SELECT *
        FROM doctor
        INNER JOIN appointment ON doctor.id = appointment.doctor_id
        ORDER BY appointment.date_time ASC
    """)
    fun getDoctorsWithAppointments() : Flow<List<DoctorWithAppointment>>


}