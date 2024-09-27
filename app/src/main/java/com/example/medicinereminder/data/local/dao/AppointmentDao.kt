package com.example.medicinereminder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.utilities.RoomConstants
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentDao {
    @Upsert
    suspend fun upsertAppointment(appointment: Appointment)

    @Delete
    suspend fun deleteAppointment(appointment: Appointment)

    @Query("""
        SELECT * FROM appointment 
        ORDER BY date_added
        """)
    fun getAllAppointments() : Flow<List<Appointment>>

}