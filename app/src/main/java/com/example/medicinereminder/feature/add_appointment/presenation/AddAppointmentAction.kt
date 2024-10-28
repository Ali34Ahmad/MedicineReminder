package com.example.medicinereminder.feature.add_appointment.presenation

import java.time.LocalDate
import java.time.LocalTime

sealed interface AddAppointmentAction {
    //top bar
    data object NavigateBack: AddAppointmentAction
    data object ShowDialogBox: AddAppointmentAction
    data object HideDialogBox: AddAppointmentAction
    data object ReplaceDoctor: AddAppointmentAction

    //doctor card
    data object ToggleDetailsVisibility: AddAppointmentAction

    //pick a date
    data class SelectDate(val date: LocalDate): AddAppointmentAction
    data object ShowCalender: AddAppointmentAction
    data object HideCalender: AddAppointmentAction

    //time picker
    data class SelectHour(val hour: Int): AddAppointmentAction
    data class SelectMinute(val minute: Int): AddAppointmentAction
    data class SelectPeriod(val period: String) : AddAppointmentAction

    //bottom bar
    data object Cancel: AddAppointmentAction
    data object Confirm: AddAppointmentAction
    data object EnableConfirmButton: AddAppointmentAction
    data object DisableConfirmButton: AddAppointmentAction
}