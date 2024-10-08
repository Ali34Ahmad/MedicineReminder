package com.example.medicinereminder.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.medicinereminder.data.local.utilities.RoomConstants
import kotlin.String

@Entity(tableName = RoomConstants.MedicineForm.TABLE_NAME)
data class MedicineForm(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    @ColumnInfo(name = RoomConstants.MedicineForm.IS_ADDED_BY_USER)
    val isAddedByUser:Boolean,
)
