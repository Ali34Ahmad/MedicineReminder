package com.example.medicinereminder.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.medicinereminder.data.model.Address
import com.example.medicinereminder.data.local.utilities.RoomConstants
import kotlin.String

@Entity(tableName = RoomConstants.Doctor.TABLE_NAME)
data class Doctor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val specialty: String,
    @Embedded val address: Address? = null,
    @ColumnInfo(name = RoomConstants.Doctor.PHONE_NUMBER) val phoneNumber: String? = null,
    @ColumnInfo(name = RoomConstants.Doctor.IMAGE_FILE_NAME) val imageFileName: String,
)
