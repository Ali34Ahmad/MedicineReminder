package com.example.medicinereminder.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.medicinereminder.utilities.RoomConstants

@Entity(
    tableName = RoomConstants.Medicine.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Doctor::class,
            parentColumns = [RoomConstants.Doctor.ID],
            childColumns = [RoomConstants.Medicine.DOCTOR_ID],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PharmaceuticalForm::class,
            parentColumns = [RoomConstants.PharmaceuticalForm.ID],
            childColumns = [RoomConstants.Medicine.PHARMACEUTICAL_FORM_ID],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(
            value = [
                RoomConstants.Medicine.DOCTOR_ID
            ]
        ),
        Index(
            value = [
                RoomConstants.Medicine.PHARMACEUTICAL_FORM_ID
            ]
        ),

    ]
)
data class Medicine(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    @ColumnInfo(name = RoomConstants.Medicine.COMPANY_NAME) val companyName: String?=null,
    @ColumnInfo(name = RoomConstants.Medicine.DOCTOR_ID) val doctorId: Int?=null,
    @ColumnInfo(name = RoomConstants.Medicine.PHARMACEUTICAL_FORM_ID) val pharmaceuticalFormId: Int,
    val note: String?=null,
    @ColumnInfo(name = RoomConstants.Medicine.CURRENT_AMOUNT) val currentAmount: Int,
    @ColumnInfo(name = RoomConstants.Medicine.DATE_ADDED) val dateAdded:Long,
    @ColumnInfo(name = RoomConstants.Medicine.LAST_MODIFIED_DATE) val lastModifiedDate:Long,
    @ColumnInfo(name = RoomConstants.Medicine.TOTAL_MEDICATION_DURATION) val totalMedicationDuration:Long?=null,
    @ColumnInfo(name = RoomConstants.Medicine.START_DATE) val startDate:Long,
    @ColumnInfo(name = RoomConstants.Medicine.IMAGE_FILE_NAME) val imageFileName: String,
)
