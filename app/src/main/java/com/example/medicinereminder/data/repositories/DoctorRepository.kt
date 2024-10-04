package com.example.medicinereminder.data.repositories

import com.example.medicinereminder.data.local.relationship.DoctorWithAppointments
import kotlinx.coroutines.flow.Flow

interface DoctorRepository {
    val dailyRepository: Flow<List<DoctorWithAppointments>>
}