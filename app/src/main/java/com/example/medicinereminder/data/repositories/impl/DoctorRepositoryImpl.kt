package com.example.medicinereminder.data.repositories.impl

import com.example.medicinereminder.data.local.dao.DoctorDao
import com.example.medicinereminder.data.local.relationship.DoctorWithAppointments
import com.example.medicinereminder.data.repositories.DoctorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val doctorTable: DoctorDao
): DoctorRepository{
    override val todayAppointments: Flow<List<DoctorWithAppointments>>
        get() = doctorTable.getDailyAppointments()
    override val appointments: Flow<List<DoctorWithAppointments>>
        get() = doctorTable.getDoctorsWithAppointments()
}