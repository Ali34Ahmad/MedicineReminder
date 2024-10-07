package com.example.medicinereminder.feature.home_screen.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.model.ReminderInfo
import com.example.medicinereminder.data.repositories.AppointmentRepository
import com.example.medicinereminder.data.repositories.DoctorRepository
import com.example.medicinereminder.data.repositories.MedicineReminderRepository
import com.example.medicinereminder.data.repositories.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Collections.emptyList
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    medicineRepository: MedicineRepository,
    doctorRepository: DoctorRepository,
    private val appointmentRepository: AppointmentRepository,
    private val medicineReminderRepository: MedicineReminderRepository
) : ViewModel() {

    val reminders: StateFlow<List<ReminderInfo>> = combine(
        medicineRepository.todayMedicineReminders,
        doctorRepository.todayAppointments
    ){ medicines,doctors ->
        ReminderInfo.mergerReminders(doctors,medicines)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _uiState = mutableStateOf(HomeUIState())
    val uiState: State<HomeUIState> = _uiState

    fun onAction(action: HomeAction){
        when(action){
            is HomeAction.UpdateTeb -> {
                _uiState.value = _uiState.value.copy(currentTab = action.tab)
            }
            is HomeAction.UpdateReminder -> {
                _uiState.value = _uiState.value.copy(selectedReminder = action.reminder)
            }
            HomeAction.OpenBottomSheet -> {
                _uiState.value = _uiState.value.copy(isBottomSheetShown = true)
            }
            HomeAction.CloseBottomSheet -> {
                _uiState.value = _uiState.value.copy(isBottomSheetShown = false)
            }
            is HomeAction.DeleteReminder -> {
                when(action.reminder.reminder){
                    is MedicineReminder -> medicineReminderRepository.deleteMedicineReminder(action.reminder.reminder)
                    is Appointment -> appointmentRepository.deleteAppointment(action.reminder.reminder)
                }
            }
            is HomeAction.UpdateReminderState -> {
                viewModelScope.launch(Dispatchers.IO) {
                    when(action.reminder.reminder){
                        is MedicineReminder -> {
                            val updatedMedicineReminder = action.reminder.reminder.copy(reminderState = action.newState)
                            medicineReminderRepository.updateMedicineReminder(updatedMedicineReminder)
                        }
                        is Appointment ->{
                            val updatedAppointment =action.reminder.reminder.copy(reminderState = action.newState)
                            appointmentRepository.updateAppointment(updatedAppointment)
                        }
                    }
                }
            }
            HomeAction.AddNewReminder -> TODO(
                "maybe we should open the bottom sheet showing the current medicines and below it show an add new medicine button"
            )

            is HomeAction.EditReminder -> TODO("not yet implemented")
            is HomeAction.ViewDetails -> TODO("not yet implemented")
        }
    }

}