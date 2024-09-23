package com.example.medicinereminder.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.medicinereminder.utilities.RoomConstants

@Entity(
    tableName = RoomConstants.AlternativeMedicine.TABLE_NAME,
    primaryKeys = [
        RoomConstants.AlternativeMedicine.ORIGINAL_MEDICINE_ID,
        RoomConstants.AlternativeMedicine.ALTERNATIVE_MEDICINE_ID,
    ],
    foreignKeys = [
        ForeignKey(
            entity = Medicine::class,
            parentColumns = [RoomConstants.Medicine.ID],
            childColumns = [RoomConstants.AlternativeMedicine.ORIGINAL_MEDICINE_ID],
            onDelete = ForeignKey.NO_ACTION,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Medicine::class,
            parentColumns = [RoomConstants.Medicine.ID],
            childColumns = [RoomConstants.AlternativeMedicine.ALTERNATIVE_MEDICINE_ID],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(value = [
            RoomConstants.AlternativeMedicine.ALTERNATIVE_MEDICINE_ID]
        ),
        Index(value = [
            RoomConstants.AlternativeMedicine.ORIGINAL_MEDICINE_ID]
        ),
    ]
)
data class AlternativeMedicine(
    @ColumnInfo(name = RoomConstants.AlternativeMedicine.ORIGINAL_MEDICINE_ID) val originalMedicineId: Int,
    @ColumnInfo(name = RoomConstants.AlternativeMedicine.ALTERNATIVE_MEDICINE_ID) val alternativeMedicineId: Int,
    @ColumnInfo(name = RoomConstants.AlternativeMedicine.REPLACEMENT_DATE) val replacementDate: Long,
)