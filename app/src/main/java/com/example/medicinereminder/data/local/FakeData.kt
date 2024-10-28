package com.example.medicinereminder.data.local

import com.example.medicinereminder.common.ext.extension.toLong
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.entity.Interaction
import com.example.medicinereminder.data.local.entity.DayProgram
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.local.entity.MedicineForm
import com.example.medicinereminder.data.local.entity.Time
import com.example.medicinereminder.data.local.relationship.DoctorWithAppointments
import com.example.medicinereminder.data.local.relationship.MedicineReminderInfo
import com.example.medicinereminder.data.local.relationship.MedicineWithFormAndConflicts
import com.example.medicinereminder.data.model.Address
import com.example.medicinereminder.data.model.DoctorWithAppointment
import com.example.medicinereminder.data.model.MedicineWithReminder
import java.time.DayOfWeek
import java.time.LocalDateTime

val doctor1 = Doctor(
    name = "Dr. Jaoher Zenah",
    phoneNumber = "1234567890",
    specialty = "Ophthalmologist",
    imageFileName = "",
    address = Address(
        stateOrGovernorate = "Syria",
        city = "Lattakia",
        street = "Al Zera'ah _ Al Awokaf"
    )
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
    pharmaceuticalFormId = 2,
    currentAmount = 10,
    dateAdded = 888888888,
    totalMedicationDuration = 1,
    startDate = 99999999999,
    lastModifiedDate = 8888888888,
    imageFileName = ""
)
val medicineFormsFakes = listOf(
    MedicineForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Injection",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Suppository",
        isAddedByUser = false
    ),MedicineForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Injection",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Suppository",
        isAddedByUser = false
    ),MedicineForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Injection",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Suppository",
        isAddedByUser = false
    ),MedicineForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Syrup",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Suspension",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Injection",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Suppository",
        isAddedByUser = false
    ),MedicineForm(
        name = "Capsule",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Tablet",
        isAddedByUser = false
    ),
    MedicineForm(
        name = "Syrup",
        isAddedByUser = false
    ),
)
val dayProgram = DayProgram(
    dayOfWeek = DayOfWeek.MONDAY,
    numberOfDosesPerDay = 2,
    medicineId = 1
)
val time = Time(
    cycleProgramId = null,
    dayProgramId = 1,
    dateAdded = 444444444,
    doseAmount = 1,
    lastModifiedDate = 33333333,
    time = 11111111111
)
val interaction =
    Interaction(
        id = 1,
    description = "Avoid tea or milk within 3 hours of taking this medication.",
    medicineId = 1
)
val reminder1 = MedicineReminder(
    id = 1,
    isRefillReminder = true,
    dateAdded = LocalDateTime.now().toLong(),
    dateTime = LocalDateTime.now().toLong(),
    reminderState = ReminderState.MISSED,
    doseAmount = 2,
    medicineId = 1
)

val reminder2 = MedicineReminder(
    id = 2,
    isRefillReminder = true,
    dateAdded = LocalDateTime.now().toLong(),
    dateTime = LocalDateTime.now().toLong()+20,
    reminderState = ReminderState.TAKEN,
    doseAmount = 2,
    medicineId = 1)
val reminder3 = MedicineReminder(
    id = 3,
    isRefillReminder = true,
    dateAdded = 0,
    dateTime = LocalDateTime.now().toLong(),
    reminderState = ReminderState.UPCOMING,
    doseAmount = 2,
    medicineId = 1)
val appointment1 = Appointment(
    id = 1,
    dateAdded = 0,
    dateTime = LocalDateTime.now().toLong()+1,
    lastModifiedDate = 0,
    reminderState = ReminderState.TAKEN,
    doctorId = 1
)
val appointment2 = Appointment(
    id = 2,
    dateAdded = 0,
    dateTime = LocalDateTime.now().toLong()-10,
    lastModifiedDate = 0,
    reminderState = ReminderState.TAKEN,
    doctorId = 1
)
val appointment3 = Appointment(
    id = 3,
    dateAdded = 0,
    dateTime = LocalDateTime.now().toLong()-10,
    lastModifiedDate = 0,
    reminderState = ReminderState.MISSED,
    doctorId = 1
)
val appointment4 = Appointment(
    id = 4,
    dateAdded = 0,
    dateTime = LocalDateTime.now().toLong(),
    lastModifiedDate = 0,
    reminderState = ReminderState.UPCOMING,
    doctorId = 1
)
val appointment5 = Appointment(
    id = 5,
    dateAdded = 0,
    dateTime = LocalDateTime.now().toLong(),
    lastModifiedDate = 0,
    reminderState = ReminderState.UPCOMING,
    doctorId = 1
)
val medicineForm1 = MedicineForm(
    id = 1 ,
    name = "Cyrup",
    false,
)
val medicineForm2 = MedicineForm(
    id = 2 ,
    name = "Pill",
    isAddedByUser = false,
)
val interaction2 = Interaction(2,"",1)
val medicinesInfo = listOf(
    MedicineReminderInfo(
        medicine = medicine1,
        reminders = listOf(reminder1, reminder2),
        medicineForm = medicineForm1, interactions = listOf(interaction, interaction2)
    ),
    MedicineReminderInfo(
        medicine = medicine2,
        reminders = listOf(reminder3),
        medicineForm = medicineForm1, interactions = listOf(interaction, interaction2)
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

val appointments = listOf(
    appointment1,
    appointment2,
    appointment3,
    appointment4,
    appointment5,
)

val timedEvents = listOf(
    DoctorWithAppointment(doctor = doctor1, appointment1),
    DoctorWithAppointment(doctor = doctor1, appointment2),
    MedicineWithReminder(medicineInfo = MedicineWithFormAndConflicts(medicine1, medicineForm1,emptyList()),
        reminder1),
    MedicineWithReminder(medicineInfo = MedicineWithFormAndConflicts(medicine1, medicineForm1,emptyList()),
        reminder2)
)