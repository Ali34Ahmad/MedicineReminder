package com.example.medicinereminder.data.local

import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.R
import com.example.medicinereminder.common.utility.extension.toLong
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.local.entity.Conflict
import com.example.medicinereminder.data.local.entity.DayProgram
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.local.entity.PharmaceuticalForm
import com.example.medicinereminder.data.local.entity.Time
import java.time.DayOfWeek
import java.time.LocalDateTime

val doctor1 = Doctor(
    name = "Dr. Jaoher Zenah",
    phoneNumber = "1234567890",
    specialty = "Dentist",
    imageFileName = ""
)
val medicine1 = Medicine(
    id=1,
    name = "Paracetamol",
    companyName = "ABC",
    pharmaceuticalFormId = 1,
    currentAmount = 10,
    dateAdded = 888888888,
    totalMedicationDuration = 1,
    startDate = 99999999999,
    lastModifiedDate = 8888888888,
    imageFileName = ""
)
val medicine2 = Medicine(
    id=1,
    name = "Paracetamol",
    companyName = "ABC",
    doctorId = 1,
    pharmaceuticalFormId = 1,
    currentAmount = 10,
    dateAdded = 888888888,
    totalMedicationDuration = 1,
    startDate = 99999999999,
    lastModifiedDate = 8888888888,
    imageFileName = ""
)
val pharmaceuticalForm1 = PharmaceuticalForm(
    name = "Capsule",
    isAddedByUser = false
)
val dayProgram = DayProgram(
    medicineId = 1,
    dayOfWeek = DayOfWeek.MONDAY,
    numberOfDosesPerDay = 2
)
val time = Time(
    cycleProgramId = null,
    dayProgramId = 1,
    dateAdded = 444444444,
    doseAmount = 1,
    lastModifiedDate = 33333333,
    time = 11111111111
)
val conflict= Conflict(
    description = "Avoid tea or milk within 3 hours of taking this medication.",
    medicineId = 1
)
val reminder1 = MedicineReminder(
    isRefillReminder = true,
    dateAdded = LocalDateTime.now().toLong(),
    dateTime = LocalDateTime.now().toLong(),
    reminderState = ReminderState.MISSED,
    doseAmount = 2,
    medicineId = 1
)
val reminder2 = MedicineReminder(
    isRefillReminder = true,
    dateAdded = LocalDateTime.now().toLong(),
    dateTime = LocalDateTime.now().toLong(),
    reminderState = ReminderState.TAKEN,
    doseAmount = 2,
    medicineId = 1
)
val reminder3 = MedicineReminder(
    isRefillReminder = true,
    dateAdded = 0,
    dateTime = 0,
    reminderState = ReminderState.UPCOMING,
    doseAmount = 2,
    medicineId = 1
)