package com.example.medicinereminder.data.local.di

import com.example.medicinereminder.data.local.AppDatabase
import com.example.medicinereminder.data.local.dao.AlternativeMedicinesDao
import com.example.medicinereminder.data.local.dao.AppointmentDao
import com.example.medicinereminder.data.local.dao.ConflictDao
import com.example.medicinereminder.data.local.dao.CycleProgramDao
import com.example.medicinereminder.data.local.dao.DayProgramDao
import com.example.medicinereminder.data.local.dao.DoctorDao
import com.example.medicinereminder.data.local.dao.MedicineDao
import com.example.medicinereminder.data.local.dao.MedicineReminderDao
import com.example.medicinereminder.data.local.dao.MedicineFormDao
import com.example.medicinereminder.data.local.dao.TimeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
 object DAOsModule{
    @Provides
    fun provideMedicineDao(appDatabase: AppDatabase): MedicineDao =
        appDatabase.medicineDao()

    @Provides
    fun providesConflictDao(appDatabase: AppDatabase): ConflictDao =
        appDatabase.conflictDao()

    @Provides
    fun providesPharmaceuticalFormDao(appDatabase: AppDatabase): MedicineFormDao =
        appDatabase.pharmaceuticalFormDao()

    @Provides
    fun providesDoctorDao(appDatabase: AppDatabase): DoctorDao =
        appDatabase.doctorDao()

    @Provides
    fun provideMedicineReminderDao(appDatabase: AppDatabase): MedicineReminderDao =
        appDatabase.medicineReminderDao()

    @Provides
    fun provideTimeDao(appDatabase: AppDatabase): TimeDao =
        appDatabase.timeDao()

    @Provides
    fun provideDayProgramDao(appDatabase: AppDatabase): DayProgramDao =
        appDatabase.dayProgramDao()
    @Provides
    fun provideAlternativeMedicineDao(appDatabase: AppDatabase): AlternativeMedicinesDao =
        appDatabase.alternativeMedicinesDao()

    @Provides
    fun provideAppointmentDao(appDatabase: AppDatabase): AppointmentDao =
        appDatabase.appointmentDao()

    @Provides
    fun provideCycleProgramDao(appDatabase: AppDatabase): CycleProgramDao =
        appDatabase.cycleProgramDao()

}











