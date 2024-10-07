package com.example.medicinereminder.feature.appointment_screen.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.common.utility.extension.toReminderInfoList
import com.example.medicinereminder.data.model.ReminderInfo
import com.example.medicinereminder.data.repositories.AppointmentRepository
import com.example.medicinereminder.data.repositories.DoctorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(
    private val appointmentRepository: AppointmentRepository,
    doctorRepository: DoctorRepository
): ViewModel() {

    val appointments: StateFlow<List<ReminderInfo>> = doctorRepository.appointments
        .map {
            val reminders: MutableList<ReminderInfo> = mutableListOf()
            it.forEach { doctorWithAppointments ->
                reminders.addAll(doctorWithAppointments.toReminderInfoList())
            }
            reminders
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(5000)
        )

    private val _uiState = mutableStateOf(AppointmentsUiState())
    val uiState: State<AppointmentsUiState> = _uiState

    fun onAction(action: AppointmentsAction){
        when(action){
            is AppointmentsAction.OpenBottomSheet -> {
                _uiState.value = _uiState.value.copy(isBottomSheetShown = true)
            }
            is AppointmentsAction.CloseBottomSheet -> {
                _uiState.value = _uiState.value.copy(isBottomSheetShown = false)
            }
            is AppointmentsAction.UpdateCurrentTab -> {
                _uiState.value = _uiState.value.copy(currentTab = action.tab)
            }
            is AppointmentsAction.UpdateSelectedReminder -> {
                _uiState.value = _uiState.value.copy(selectedReminder = action.reminder)
            }
            is AppointmentsAction.UpdateAppointment -> {
                viewModelScope.launch(Dispatchers.IO) {
                    appointmentRepository.updateAppointment(action.appointment)
                }
            }
            is AppointmentsAction.UpdateAppointmentState -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedAppointment = action.appointment.copy( reminderState = action.state )
                    viewModelScope.launch(Dispatchers.IO) {
                        appointmentRepository.updateAppointment(updatedAppointment)
                    }
                }
            }
            is AppointmentsAction.DeleteAppointment ->{
                appointmentRepository.deleteAppointment(action.appointment)
            }
        }
    }

}