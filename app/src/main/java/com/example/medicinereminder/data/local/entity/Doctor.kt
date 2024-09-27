package com.example.medicinereminder.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.medicinereminder.data.model.Address
import com.example.medicinereminder.data.model.Location
import com.example.medicinereminder.utilities.RoomConstants

@Entity(tableName = RoomConstants.Doctor.TABLE_NAME)
data class Doctor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val specialty: String,
    @Embedded val address: Address?=null,
    @Embedded val location: Location?=null,//we will LatLng class instead of this class
    @ColumnInfo(name = RoomConstants.Doctor.PHONE_NUMBER) val phoneNumber: String?=null,
)
