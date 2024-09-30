package com.example.medicinereminder.data.local

import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.R
import com.example.medicinereminder.data.local.entity.Conflict
import com.example.medicinereminder.data.local.entity.DayProgram
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.PharmaceuticalForm
import com.example.medicinereminder.data.local.entity.Time
import java.time.DayOfWeek

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