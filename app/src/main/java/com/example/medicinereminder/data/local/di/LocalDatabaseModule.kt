package com.example.medicinereminder.data.local.di

import android.content.Context
import com.example.medicinereminder.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 object LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(
        @ApplicationContext context: Context
    ) : AppDatabase =  AppDatabase.getInstance(context)

}