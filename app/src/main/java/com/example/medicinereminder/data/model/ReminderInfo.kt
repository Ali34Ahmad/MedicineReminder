package com.example.medicinereminder.data.model

import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.enums.ReminderType
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.entity.Conflict
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.local.entity.Medicine
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.local.entity.PharmaceuticalForm
import com.example.medicinereminder.data.local.relationship.DoctorWithAppointments
import com.example.medicinereminder.data.local.relationship.MedicineReminderInfo
import com.example.medicinereminder.data.local.relationship.MedicineWithMedicineReminder

data class ReminderInfo(
    val medicine: Medicine?,
    val conflicts: List<Conflict>,
    val pharmaceuticalForm: PharmaceuticalForm?,
    val doctor: Doctor?,
    val reminder: Reminder,
    val type: ReminderType
){
    companion object{
        fun mergerReminders(
            doctorsWithAppointments: List<DoctorWithAppointments>,
            medicineWithReminders: List<MedicineReminderInfo>
        ): List<ReminderInfo> {
            val reminders = mutableListOf<ReminderInfo>()
            doctorsWithAppointments.forEach { doctorInfo ->
                doctorInfo.appointments.forEach {
                    reminders.add(
                        ReminderInfo(
                            doctor = doctorInfo.doctor,
                            reminder = it,
                            type = ReminderType.APPOINTMENT,
                            medicine = null,
                            pharmaceuticalForm = null,
                            conflicts = emptyList()
                        )
                    )
                }
            }
            medicineWithReminders.forEach { medicineInfo ->
                medicineInfo.reminders.forEach {
                    reminders.add(
                        ReminderInfo(
                            medicine = medicineInfo.medicine,
                            reminder = it,
                            type = ReminderType.MEDICINE,
                            conflicts = medicineInfo.conflicts,
                            pharmaceuticalForm = medicineInfo.pharmaceuticalForm,
                            doctor = null
                        )
                    )
                }
            }

            return reminders.sortedBy { it.reminder.dateTime }
        }
    }
}

//Don't try this at home.
fun main() {
    val x = DoctorWithAppointments(
        doctor = Doctor(
            imageFileName = "",
            id = 1,
            specialty = "a",
            name = "ali",
        ),
        appointments = listOf(
            Appointment(
                id = 1, reminderState = ReminderState.MISSED,
                dateAdded = 10,
                dateTime = 20,
                lastModifiedDate = 20,
                doctorId = 1
            ),
            Appointment(
                id = 2, reminderState = ReminderState.MISSED,
                dateAdded = 10,
                dateTime = 30,
                lastModifiedDate = 30,
                doctorId = 1
            )
        )
    )
    val x2 = DoctorWithAppointments(
        doctor = Doctor(
            imageFileName = "",
            id = 2,
            specialty = "a",
            name = "zen",
        ),
        appointments = listOf(
            Appointment(
                id = 1, reminderState = ReminderState.MISSED,
                dateAdded = 10,
                dateTime = 22,
                lastModifiedDate = 20,
                doctorId = 2
            ),
            Appointment(
                id = 2, reminderState = ReminderState.UPCOMING,
                dateAdded = 10,
                dateTime = 32,
                lastModifiedDate = 30,
                doctorId = 2
            )
        )
    )
    val y = MedicineReminderInfo(
        conflicts = emptyList(),
        pharmaceuticalForm = PharmaceuticalForm(0,"",false),
        medicine = Medicine(
            id = 1 ,
            name = "D",
            imageFileName = "",
            dateAdded = 10,
            currentAmount = 10,
            startDate = 24,
            pharmaceuticalFormId = 3,
            lastModifiedDate = 30,
        ),
        reminders = listOf(
            MedicineReminder(
                id = 1,
                dateTime = 9,
                dateAdded = 8,
                reminderState = ReminderState.TAKEN,
                medicineId = 1,
                doseAmount = 3,
                isRefillReminder = true
            ),
            MedicineReminder(
                id = 2,
                dateTime = 44,
                dateAdded = 8,
                reminderState = ReminderState.TAKEN,
                medicineId = 1,
                doseAmount = 3,
                isRefillReminder = true
            ),
            MedicineReminder(
                id = 2,
                dateTime = 18,
                dateAdded = 8,
                reminderState = ReminderState.TAKEN,
                medicineId = 1,
                doseAmount = 3,
                isRefillReminder = true
            ),
        )
    )
    val y2 = MedicineReminderInfo(
        conflicts = emptyList(),
        pharmaceuticalForm = PharmaceuticalForm(0,"",false),
        medicine = Medicine(
            id = 2 ,
            name = "F",
            imageFileName = "",
            dateAdded = 12,
            currentAmount = 10,
            startDate = 24,
            pharmaceuticalFormId = 3,
            lastModifiedDate = 30,
        ),
        reminders = listOf(
            MedicineReminder(
                id = 1,
                dateTime = 11,
                dateAdded = 8,
                reminderState = ReminderState.TAKEN,
                medicineId = 2,
                doseAmount = 3,
                isRefillReminder = true
            ),
            MedicineReminder(
                id = 2,
                dateTime = 32,
                dateAdded = 8,
                reminderState = ReminderState.TAKEN,
                medicineId = 2,
                doseAmount = 3,
                isRefillReminder = true
            ),
            MedicineReminder(
                id = 2,
                dateTime = 24,
                dateAdded = 8,
                reminderState = ReminderState.TAKEN,
                medicineId = 2,
                doseAmount = 3,
                isRefillReminder = true
            ),
        )
    )
    val reminders = ReminderInfo.mergerReminders(
        medicineWithReminders = listOf(y,y2),
        doctorsWithAppointments = listOf(x,x2)
    )
    reminders.forEach {
        println("_____________________________________________________________________________________________________ \n")
        println(it.toString())
    }
}