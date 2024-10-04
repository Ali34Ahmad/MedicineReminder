package com.example.medicinereminder.data.local

import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.R
import com.example.medicinereminder.common.utility.extension.toLong
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.entity.Conflict
import com.example.medicinereminder.data.local.entity.DayProgram
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.local.entity.PharmaceuticalForm
import com.example.medicinereminder.data.local.entity.Time
import com.example.medicinereminder.data.local.relationship.DoctorWithAppointments
import com.example.medicinereminder.data.local.relationship.MedicineReminderInfo
import com.example.medicinereminder.data.model.ReminderInfo
import java.time.DayOfWeek
import java.time.LocalDateTime

val doctor1 = Doctor(
    name = "Dr. Jaoher Zenah",
    phoneNumber = "1234567890",
    specialty = "Dentist",
    imageFileName = ""
)
val doctor2 = Doctor(
    name = "Dr. Ali Al Sisi",
    phoneNumber = "1234567890",
    specialty = "Dentist",
    imageFileName = ""
)
val medicine1 = Medicine(
    id = 1,
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
    id=2,
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
val pharmaceuticalForms = listOf(
    PharmaceuticalForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Injection",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suppository",
        isAddedByUser = false
    ),PharmaceuticalForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Injection",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suppository",
        isAddedByUser = false
    ),PharmaceuticalForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Injection",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suppository",
        isAddedByUser = false
    ),PharmaceuticalForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Injection",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suppository",
        isAddedByUser = false
    ),PharmaceuticalForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Injection",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suppository",
        isAddedByUser = false
    ),PharmaceuticalForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Injection",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suppository",
        isAddedByUser = false
    ),PharmaceuticalForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Injection",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suppository",
        isAddedByUser = false
    ),PharmaceuticalForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Injection",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suppository",
        isAddedByUser = false
    ),PharmaceuticalForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Injection",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suppository",
        isAddedByUser = false
    ),PharmaceuticalForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Injection",
        isAddedByUser = false
    ),
    PharmaceuticalForm(
        name = "Suppository",
        isAddedByUser = false
    ),PharmaceuticalForm(
        name = "Capsule",
        isAddedByUser = true
    ),
    PharmaceuticalForm(
        name = "Tablet",
        isAddedByUser = true
    ),
    PharmaceuticalForm(
        name = "Syrup",
        isAddedByUser = true
    ),
    PharmaceuticalForm(
        name = "Suspension",
        isAddedByUser = true
    ),
    PharmaceuticalForm(
        name = "Injection",
        isAddedByUser = true
    ),
    PharmaceuticalForm(
        name = "Suppository",
        isAddedByUser = false
    ),
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
val conflict = Conflict(
    description = "Avoid tea or milk within 3 hours of taking this medication.",
    medicineId = 1
)
val reminder1 = MedicineReminder(
    isRefillReminder = true,
    dateAdded = LocalDateTime.now().toLong(),
    dateTime = LocalDateTime.now().toLong(),
    reminderState = ReminderState.MISSED,
    doseAmount = 2,
    medicineId = 1,
    id = 1
)
val reminder2 = MedicineReminder(
    id = 2,
    isRefillReminder = true,
    dateAdded = LocalDateTime.now().toLong(),
    dateTime = LocalDateTime.now().toLong(),
    reminderState = ReminderState.TAKEN,
    doseAmount = 2,
    medicineId = 1
)
val reminder3 = MedicineReminder(
    id = 3,
    isRefillReminder = true,
    dateAdded = 0,
    dateTime = LocalDateTime.now().toLong(),
    reminderState = ReminderState.UPCOMING,
    doseAmount = 2,
    medicineId = 1
)
val appointment1 = Appointment(
    id = 1,
    dateAdded = 0,
    dateTime = LocalDateTime.now().toLong(),
    lastModifiedDate = 0,
    reminderState = ReminderState.TAKEN,
    doctorId = 1
)
val appointment2 = Appointment(
    id = 2,
    dateAdded = 0,
    dateTime = LocalDateTime.now().toLong(),
    lastModifiedDate = 0,
    reminderState = ReminderState.TAKEN,
    doctorId = 1
)
val pharmaceuticalForm1 = PharmaceuticalForm(
    id = 1 ,
    name = "Cyrup",
    false
)
val pharmaceuticalForm2 = PharmaceuticalForm(
    id = 2 ,
    name = "Pill",
    false
)
val conflict2 = Conflict(1,"",1)
val medicinesInfo = listOf(
    MedicineReminderInfo(
        medicine = medicine1,
        reminders = listOf(reminder1, reminder2),
        pharmaceuticalForm = pharmaceuticalForm1, conflicts = listOf(conflict, conflict2)
    ),
    MedicineReminderInfo(
        medicine = medicine2,
        reminders = listOf(reminder3),
        pharmaceuticalForm = pharmaceuticalForm1, conflicts = listOf(conflict, conflict2)
    ),
)
val doctorsAppointments = listOf(
    DoctorWithAppointments(
        doctor = doctor1,
        listOf(appointment1)
    ),
    DoctorWithAppointments(
        doctor = doctor2,
        listOf(appointment2)
    ),
)
val remindersInfo = ReminderInfo.mergerReminders(doctorsAppointments, medicinesInfo)