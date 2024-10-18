package com.example.medicinereminder.feature.doctor_details.presentation

import com.example.medicinereminder.common.model.AppointmentTableItemInfo
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.model.Address
import com.example.medicinereminder.feature.appointment_screen.presentation.appointments_main.AppointmentsTab

sealed interface DoctorDetailsAction {
    data object NavigateBack: DoctorDetailsAction
    //options menu
    data object OpenOptionsMenu: DoctorDetailsAction
    data object CloseOptionsMenu: DoctorDetailsAction
    data class EditDoctor(val doctorId: String): DoctorDetailsAction
    data object DeleteDoctor: DoctorDetailsAction //need navigation first
    data object OpenExtraDetailsMenu: DoctorDetailsAction
    data object CloseExtraDetailsMenu: DoctorDetailsAction
    data class EditAll(val doctorId: Int): DoctorDetailsAction
    data class EditDetails(val doctorId: Int): DoctorDetailsAction
    data class EditImage(val doctorId: Int): DoctorDetailsAction


    //top app bar actions
    data class UpdateTopAppBarState(val newState: TopAppBarState): DoctorDetailsAction
    data object ShowEditButton: DoctorDetailsAction
    data object HideEditButton: DoctorDetailsAction
    data class EditAppointment(val appointmentId: Int): DoctorDetailsAction //need navigation
    data object DeleteAppointments: DoctorDetailsAction

    //Contact info
    data class GoToContacts(val phoneNumber: String): DoctorDetailsAction
    data class GoToMap(val address: Address):DoctorDetailsAction

    //Appointment table
    data class UpdateTap(val tab: AppointmentsTab): DoctorDetailsAction
    data class UpdateTableItemSelection(val tableItem: AppointmentTableItemInfo): DoctorDetailsAction

    // Bottom bar buttons
    data object StopReminders: DoctorDetailsAction //when the list of selected appointments is empty then stop all reminders
    data class BookAppointment(val doctorId: Int): DoctorDetailsAction //need navigation
    data object ShowMarkAsButton: DoctorDetailsAction
    data object HideMarkAsButton: DoctorDetailsAction
    data object OpenMarkAsMenu: DoctorDetailsAction
    data object CloseMarkAsMenu: DoctorDetailsAction
    data class ChangeAppointmentsState(val newState: ReminderState): DoctorDetailsAction //we will get the selected items in the ViewModel

    //Dialog boxes
    data object ShowDeleteDoctorDialogBox: DoctorDetailsAction
    data object ShowDeleteRemindersDialogBox: DoctorDetailsAction
    data object ShowStopRemindersDialogBox: DoctorDetailsAction
    data object HideDialogBox: DoctorDetailsAction
}
