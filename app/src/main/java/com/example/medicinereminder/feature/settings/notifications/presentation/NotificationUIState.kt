package com.example.medicinereminder.feature.settings.notifications.presentation

import java.time.LocalTime

data class NotificationUIState(
    //Sound's settings
    val isVibrationActivated: Boolean = true,
    val isRingtonesListVisible: Boolean = false,
    val ringtone: String = "Yanni (Rani Maker)",
    val soundVolume: Float = 0.5f,

    //Medicine reminder's settings
    val isMedicineImageVisible: Boolean = true,
    val isMedicineConflictsVisible: Boolean = true,
    val isMedicineNotesVisible: Boolean = true,
    val isMedicineRemainingTimeVisible: Boolean = true,

    //Doctor's settings
    val isDoctorImageVisible: Boolean = true,
    val isAppointmentRemainingTimeVisible: Boolean = true,

    //Reminder's behavior
    val isRemindersActivated: Boolean = true,
    val isRepetitionActivated: Boolean = true,
    val isNumberOfRepeatsLisVisible: Boolean = false,
    val numberOfRepeats: Int = 1,
    val isRepeatIntervalsListVisible: Boolean = false,
    val repeatInterval: LocalTime = LocalTime.of(0,5)//5 minutes
)
