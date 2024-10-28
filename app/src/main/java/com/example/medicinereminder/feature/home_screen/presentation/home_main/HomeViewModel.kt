package com.example.medicinereminder.feature.home_screen.presentation.home_main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.data.model.TimedEvent
import com.example.medicinereminder.domain.usecase.doctor_use_cases.GetTodayDoctorsWithAppointmentsUseCase
import com.example.medicinereminder.domain.usecase.medicine_use_cases.GetTodayMedicinesWithRemindersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getTodayDoctorsWithAppointmentsUseCase: GetTodayDoctorsWithAppointmentsUseCase,
    getTodayMedicinesWithRemindersUseCase: GetTodayMedicinesWithRemindersUseCase
) : ViewModel() {

    val reminders: StateFlow<List<TimedEvent>> = getTodayMedicinesWithRemindersUseCase().combine(
        getTodayDoctorsWithAppointmentsUseCase()
    ){ medicinesReminders,doctorsAppointments ->
        medicinesReminders.plus(doctorsAppointments).sortedBy { it.dateTime }
    }.stateIn(
        scope = viewModelScope,
        initialValue = emptyList(),
        started = SharingStarted.WhileSubscribed(5000)
    )

    private val _uiState = mutableStateOf(HomeUIState())
    val uiState: State<HomeUIState> = _uiState

    fun onAction(action: HomeAction){
        when(action){
            HomeAction.CloseBottomSheet -> {
                _uiState.value = _uiState.value.copy(
                    shownBottomSheet = HomeBottomSheet.ClOSE,
                    selectedReminder = null
                )
            }
            is HomeAction.OpenBottomSheet -> {
                _uiState.value = _uiState.value.copy(
                    shownBottomSheet = action.bottomSheet,
                    selectedReminder = action.reminder
                )
            }
            is HomeAction.UpdateTeb -> {
                _uiState.value = _uiState.value.copy(
                    currentTab = action.tab
                )
            }
        }
    }
}