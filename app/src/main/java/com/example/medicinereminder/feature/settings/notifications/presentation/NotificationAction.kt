package com.example.medicinereminder.feature.settings.notifications.presentation

import java.time.LocalTime


sealed interface NotificationAction {
    //Top app bar actions
    data object NavigateUp: NotificationAction

    //Sound's settings
    data object ToggleVibration: NotificationAction
    data class ChangeRingtone(val ringtone: String): NotificationAction
    data class ChangeSoundVolume(val volume: Float): NotificationAction
    //Media reminder content
    data object ToggleMedicineImageVisibility: NotificationAction

    data object ToggleMedicineConflictsVisibility: NotificationAction
    data object ToggleMedicineNotesVisibility: NotificationAction
    data object ToggleMedicineRemainingTimeVisibility: NotificationAction
    //Appointment reminder content
    data object ToggleDoctorImageVisibility: NotificationAction

    data object ToggleAppointmentRemainingTimeVisibility: NotificationAction
    //Reminder behavior
    data object ToggleReminderActivation: NotificationAction

    data object ToggleRepeatsActivation: NotificationAction
    data class ChangeNumberOfRepeats(val numberOfRepeats: Int): NotificationAction
    data class ChangeRepeatIntervals(val repeatInterval: LocalTime): NotificationAction

    //dialog boxes
    data object ShowRingtoneList: NotificationAction
    data object HideRingtoneList: NotificationAction

    data object ShowNumberOfRepeatsList: NotificationAction
    data object HideNumberOfRepeatsList: NotificationAction

    data object ShowRepeatIntervalsList: NotificationAction
    data object HideRepeatIntervalsList: NotificationAction
}
