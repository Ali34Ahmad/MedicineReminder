package com.example.medicinereminder.feature.settings.notifications.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(

): ViewModel(){
    //get the selected ringtone and the selected number of repeats from the data store
    //need to save the ringtones list in the database
    private val _uiState = mutableStateOf(NotificationUIState())
    val uiState: State<NotificationUIState> get() = _uiState

    fun onAction(action: NotificationAction){
        when(action){
            is NotificationAction.ChangeNumberOfRepeats -> {
                _uiState.value = _uiState.value.copy(numberOfRepeats = action.numberOfRepeats)
            }
            is NotificationAction.ChangeRepeatIntervals -> {
                _uiState.value = _uiState.value.copy(repeatInterval = action.repeatInterval)
            }
            is NotificationAction.ChangeRingtone -> {
                _uiState.value = _uiState.value.copy(ringtone = action.ringtone)
            }
            is NotificationAction.ChangeSoundVolume -> {
                _uiState.value = _uiState.value.copy(soundVolume = action.volume)
            }
            NotificationAction.ToggleAppointmentRemainingTimeVisibility -> {
                _uiState.value = _uiState.value.copy(isAppointmentRemainingTimeVisible = !_uiState.value.isAppointmentRemainingTimeVisible)
            }
            NotificationAction.ToggleDoctorImageVisibility -> {
                _uiState.value = _uiState.value.copy(isDoctorImageVisible = !_uiState.value.isDoctorImageVisible)
            }
            NotificationAction.ToggleMedicineConflictsVisibility -> {
                _uiState.value = _uiState.value.copy(isMedicineConflictsVisible = !_uiState.value.isMedicineConflictsVisible)
            }
            NotificationAction.ToggleMedicineImageVisibility -> {
                _uiState.value = _uiState.value.copy(isMedicineImageVisible = !_uiState.value.isMedicineImageVisible)
            }
            NotificationAction.ToggleMedicineNotesVisibility -> {
                _uiState.value = _uiState.value.copy(isMedicineNotesVisible = !_uiState.value.isMedicineNotesVisible)
            }
            NotificationAction.ToggleMedicineRemainingTimeVisibility -> {
                _uiState.value = _uiState.value.copy(isMedicineRemainingTimeVisible = !_uiState.value.isMedicineRemainingTimeVisible)
            }
            NotificationAction.ToggleReminderActivation ->{
                _uiState.value = _uiState.value.copy(isRemindersActivated = !_uiState.value.isRemindersActivated)

            }
            NotificationAction.ToggleRepeatsActivation -> {
                _uiState.value = _uiState.value.copy(isRepetitionActivated = !_uiState.value.isRepetitionActivated)

            }
            NotificationAction.ToggleVibration -> {
                _uiState.value = _uiState.value.copy(isVibrationActivated = !_uiState.value.isVibrationActivated)
            }

            //dialog boxes
            NotificationAction.ShowNumberOfRepeatsList -> {
                _uiState.value = _uiState.value.copy(isNumberOfRepeatsLisVisible = true)
            }
            NotificationAction.ShowRepeatIntervalsList -> {
                _uiState.value = _uiState.value.copy(isRepeatIntervalsListVisible = true)
            }
            NotificationAction.ShowRingtoneList ->{
                _uiState.value = _uiState.value.copy(isRingtonesListVisible = true)
            }
            NotificationAction.HideNumberOfRepeatsList -> {
                _uiState.value = _uiState.value.copy(isNumberOfRepeatsLisVisible = false)
            }
            NotificationAction.HideRepeatIntervalsList -> {
                _uiState.value = _uiState.value.copy(isRepeatIntervalsListVisible = false)
            }
            NotificationAction.HideRingtoneList ->{
                _uiState.value = _uiState.value.copy(isRingtonesListVisible = false)
            }
            NotificationAction.NavigateUp -> TODO("need for navigation")
        }
    }
}