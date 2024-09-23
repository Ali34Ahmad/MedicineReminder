package com.example.medicinereminder.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.medicinereminder.utilities.RoomConstants

@Entity(tableName =RoomConstants.PharmaceuticalForm.TABLE_NAME)
data class PharmaceuticalForm(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    @ColumnInfo(name = RoomConstants.PharmaceuticalForm.IS_ADDED_BY_USER)
    val isAddedByUser:Boolean,
)
