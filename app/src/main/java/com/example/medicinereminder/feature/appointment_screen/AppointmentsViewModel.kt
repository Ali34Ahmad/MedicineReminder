package com.example.medicinereminder.feature.appointment_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.common.utility.extension.toReminderInfoList
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.model.ReminderInfo
import com.example.medicinereminder.data.repositories.AppointmentRepository
import com.example.medicinereminder.data.repositories.DoctorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(
    private val appointmentRepository: AppointmentRepository,
    private val doctorRepository: DoctorRepository
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


    private val _uiState = MutableStateFlow(AppointmentsUiState())
    val uiState: StateFlow<AppointmentsUiState> = _uiState.asStateFlow()

    fun openBottomSheet(){
       _uiState.value = _uiState.value.copy(isBottomSheetShown = true)
    }
    fun closeBottomSheet(){
        _uiState.value = _uiState.value.copy(isBottomSheetShown = false)
    }
    fun updateCurrentTab(tab: AppointmentsTab){
        _uiState.value = _uiState.value.copy(currentTab = tab)
    }
    fun updateSelectedReminder(appointment: ReminderInfo){
        _uiState.value = _uiState.value.copy(selectedReminder = appointment)
    }

    fun updateAppointment(appointment: Appointment){
        viewModelScope.launch(Dispatchers.IO) {
            appointmentRepository.updateAppointment(appointment)
        }
    }
    fun updateAppointmentState(appointment: Appointment, state: ReminderState){
        viewModelScope.launch(Dispatchers.IO) {
            val updatedAppointment = appointment.copy( reminderState = state )
            viewModelScope.launch(Dispatchers.IO) {
                appointmentRepository.updateAppointment(updatedAppointment)
            }
        }
    }
    fun deleteAppointment(appointment: Appointment){
        viewModelScope.launch(Dispatchers.IO) {
            appointmentRepository.deleteAppointment(appointment)
        }
    }


}