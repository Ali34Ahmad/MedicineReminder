package com.example.medicinereminder.data.repositories.di

import com.example.medicinereminder.data.repositories.AppointmentRepository
import com.example.medicinereminder.data.repositories.DoctorRepository
import com.example.medicinereminder.data.repositories.MedicineReminderRepository
import com.example.medicinereminder.data.repositories.MedicineRepository
import com.example.medicinereminder.data.repositories.impl.AppointmentRepositoryImpl
import com.example.medicinereminder.data.repositories.impl.DoctorRepositoryImpl
import com.example.medicinereminder.data.repositories.impl.MedicineReminderRepositoryImpl
import com.example.medicinereminder.data.repositories.impl.MedicineRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun bindMedicineRepository(
        medicineRepositoryImpl: MedicineRepositoryImpl
    ): MedicineRepository
    @Binds
    @Singleton
    abstract fun bindDoctorRepository(
        doctorRepositoryImpl: DoctorRepositoryImpl
    ): DoctorRepository

    @Binds
    @Singleton
    abstract fun bindAppointmentRepository(
        appointmentRepositoryImpl: AppointmentRepositoryImpl
    ): AppointmentRepository

    @Binds
    @Singleton
    abstract fun bindMedicineReminderRepository(
        medicineRepositoryImpl: MedicineReminderRepositoryImpl
    ): MedicineReminderRepository



}