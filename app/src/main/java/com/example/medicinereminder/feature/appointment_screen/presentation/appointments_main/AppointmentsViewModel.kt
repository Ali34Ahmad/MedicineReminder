package com.example.medicinereminder.feature.appointment_screen.presentation.appointments_main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.data.model.DoctorWithAppointment
import com.example.medicinereminder.domain.usecase.doctor_use_cases.GetDoctorsWithAppointmentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(
    private val getAllDoctorWithAppointment: GetDoctorsWithAppointmentsUseCase
): ViewModel() {

    val reminders = getAllDoctorWithAppointment()
        .stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(5000)
        )

    private val _uiState = mutableStateOf(AppointmentsUiState())
    val uiState: State<AppointmentsUiState> = _uiState

    fun onAction(action: AppointmentsAction){
        when(action){
            AppointmentsAction.CloseBottomSheet -> {
                _uiState.value = _uiState.value.copy(shownBottomSheet = AppointmentBottomSheet.CLOSE)
            }
            is AppointmentsAction.OpenBottomSheet ->{
                _uiState.value = _uiState.value.copy(
                    shownBottomSheet = action.bottomSheet,
                    selectedReminder = action.reminder
                )
            }
            is AppointmentsAction.UpdateTab -> {
                _uiState.value = _uiState.value.copy(currentTab = action.tab)
            }
        }
    }

}