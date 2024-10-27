package com.example.medicinereminder.feature.add_appointment.presenation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.common.ext.extension.toLong
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.domain.usecase.appointment_use_cases.AddAppointmentUseCase
import com.example.medicinereminder.domain.usecase.doctor_use_cases.GetDoctorByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AddAppointmentViewModel @Inject constructor(
    private val getDoctorByIdUseCase: GetDoctorByIdUseCase,
    private val addAppointmentUseCase: AddAppointmentUseCase
): ViewModel() {

    //replace it from nav controller
    private val fakeId = 1


    private val _uiState = mutableStateOf(AddAppointmentUIState())
    val uiState : State<AddAppointmentUIState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val doctor = getDoctorByIdUseCase(fakeId).first()
            _uiState.value = _uiState.value.copy(doctor = doctor)
        }
    }

    fun onAction(action: AddAppointmentAction){
        when(action){
            AddAppointmentAction.Confirm -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        val extraHours = if(_uiState.value.selectedPeriod=="PM") 12 else 0
                        val time = LocalTime.of(
                        _uiState.value.selectedHour + extraHours,
                        _uiState.value.selectedMinute,
                        )
                        val dateTime = LocalDateTime.of(_uiState.value.selectedDate,time)
                        val appointment = Appointment(
                            doctorId = _uiState.value.doctor.id,
                            dateTime = dateTime.toLong(),
                            reminderState = ReminderState.UPCOMING,
                            dateAdded = LocalDateTime.now().toLong(),
                            lastModifiedDate = LocalDateTime.now().toLong()
                        )
                        addAppointmentUseCase(appointment)

                        TODO("need navigation to the home screen")
                    }
                }
            AddAppointmentAction.HideCalender -> {
                _uiState.value = _uiState.value.copy(isCalenderShown = false)
            }
            AddAppointmentAction.ShowCalender -> {
                _uiState.value = _uiState.value.copy(isCalenderShown = true)
            }
            AddAppointmentAction.ToggleDetailsVisibility -> {
                _uiState.value = _uiState.value.copy(isDetailsShown = !_uiState.value.isDetailsShown)
            }
            is AddAppointmentAction.SelectDate -> {
                _uiState.value = _uiState.value.copy(selectedDate = action.date)
                if(!_uiState.value.isConfirmButtonEnabled){
                    onAction(
                        AddAppointmentAction.EnableConfirmButton
                    )
                }

            }
            AddAppointmentAction.DisableConfirmButton -> {
                _uiState.value = _uiState.value.copy(isConfirmButtonEnabled = false)
            }
            AddAppointmentAction.EnableConfirmButton -> {
                _uiState.value = _uiState.value.copy(isCalenderShown = true)
            }
            AddAppointmentAction.HideDialogBox ->{
                _uiState.value = _uiState.value.copy(isDialogBoxShown = false)
            }
            AddAppointmentAction.ShowDialogBox -> {
                _uiState.value = _uiState.value.copy(isDialogBoxShown = true)
            }
            AddAppointmentAction.ReplaceDoctor -> TODO("need navigate to the add doctor screen")
            AddAppointmentAction.NavigateBack -> TODO("need navigation")
            AddAppointmentAction.Cancel -> TODO("navigate back using navigation")
            //time picker
            is AddAppointmentAction.SelectHour -> {
                _uiState.value = _uiState.value.copy(selectedHour = action.hour)
            }
            is AddAppointmentAction.SelectMinute -> {
                _uiState.value = _uiState.value.copy(selectedHour = action.minute)
            }
            is AddAppointmentAction.SelectPeriod -> {
                _uiState.value = _uiState.value.copy(selectedPeriod = action.period)
            }
        }
    }

}