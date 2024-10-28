package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.medicinereminder.data.local.entity.Appointment
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addNewAppointment(appointment: Appointment)

    @Update
    suspend fun updateAppointment(appointment: Appointment)

    @Delete
    fun deleteAppointment(appointment: Appointment)

    @Query("""
        SELECT * 
        FROM appointment
        WHERE doctor_id = :id
    """)
    fun getAppointmentsByDoctorId(id: Int) : Flow<List<Appointment>>

}