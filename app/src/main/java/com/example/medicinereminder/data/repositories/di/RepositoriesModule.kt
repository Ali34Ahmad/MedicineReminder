package com.example.medicinereminder.data.repositories.di

import com.example.medicinereminder.domain.repository.AppointmentRepository
import com.example.medicinereminder.domain.repository.DoctorRepository
import com.example.medicinereminder.domain.repository.MedicineReminderRepository
import com.example.medicinereminder.domain.repository.MedicineRepository
import com.example.medicinereminder.data.repositories.impl.AppointmentRepositoryImpl
import com.example.medicinereminder.data.repositories.impl.DoctorRepositoryImpl
import com.example.medicinereminder.data.repositories.impl.MedicineReminderRepositoryImpl
import com.example.medicinereminder.data.repositories.impl.MedicineRepositoryImpl
import com.example.medicinereminder.feature.add_medicine.data.MedicineFormRepositoryImpl
import com.example.medicinereminder.feature.add_medicine.domain.repository.MedicineFormRepository
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
        medicineReminderRepositoryImpl: MedicineReminderRepositoryImpl
    ): MedicineReminderRepository

    @Binds
    @Singleton
    abstract fun bindMedicineFormRepository(
        medicineFormRepositoryImpl: MedicineFormRepositoryImpl
    ): MedicineFormRepository

}