package com.example.medicinereminder.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.medicinereminder.data.local.dao.AlternativeMedicinesDao
import com.example.medicinereminder.data.local.dao.ConflictDao
import com.example.medicinereminder.data.local.dao.DayProgramDao
import com.example.medicinereminder.data.local.dao.MedicineDao
import com.example.medicinereminder.data.local.dao.TimeDao
import com.example.medicinereminder.data.local.entity.AlternativeMedicine
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.entity.Conflict
import com.example.medicinereminder.data.local.entity.CycleProgram
import com.example.medicinereminder.data.local.entity.DayProgram
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.local.entity.PharmaceuticalForm
import com.example.medicinereminder.data.local.entity.Time
import com.example.medicinereminder.utilities.RoomConstants

//TODO("Set exportSchema to true in the final product")
@Database(
    version = RoomConstants.DATABASE_VERSION, exportSchema = false,
    entities = [
        AlternativeMedicine::class,
        Appointment::class,
        Conflict::class,
        CycleProgram::class,
        DayProgram::class,
        Doctor::class,
        Medicine::class,
        MedicineReminder::class,
        PharmaceuticalForm::class,
        Time::class,
    ]
)

abstract class AppDatabase:RoomDatabase() {

    abstract fun medicineDao(): MedicineDao
    abstract fun conflictDao(): ConflictDao
    abstract fun alternativeMedicinesDao(): AlternativeMedicinesDao
    abstract fun dayProgramDao(): DayProgramDao
    abstract fun timeDao(): TimeDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, RoomConstants.DATABASE_NAME)
            //TODO("Build DB in the background to make sure it is built even when the user exit the app for the first time")

//                .addCallback(
//                    object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//
//                            val request = OneTimeWorkRequestBuilder<DatabaseWorker>()
//                                .setInputData(workDataOf(KEY_FILENAME to PLANT_DATA_FILENAME))
//                                .build()
//                            WorkManager.getInstance(context).enqueue(request)
//                        }
//                    }
//                )
                //TODO("Remove fallbackToDestructiveMigration in the final product")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}