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

                        val dateTime = LocalDateTime.of(
                        uiState.value.selectedDate?: LocalDate.now(),
                        _uiState.value.selectedTime?: LocalTime.now()
                        )

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
            }
            is AddAppointmentAction.SelectTime -> {
                _uiState.value = _uiState.value.copy(selectedTime = action.time)
                if(_uiState.value.selectedTime != null && _uiState.value.selectedDate != null && !_uiState.value.isConfirmButtonEnabled)
                    onAction(
                        AddAppointmentAction.EnableConfirmButton
                    )
                else if(
                    _uiState.value.isConfirmButtonEnabled
                    && !(_uiState.value.selectedTime != null && _uiState.value.selectedDate == null)
                    ){
                    onAction(
                        AddAppointmentAction.DisableConfirmButton
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
        }
    }

}