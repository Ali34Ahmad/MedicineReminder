package com.example.medicinereminder.feature.homeScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.enums.ReminderType
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.model.ReminderInfo
import com.example.medicinereminder.data.repositories.AppointmentRepository
import com.example.medicinereminder.data.repositories.DoctorRepository
import com.example.medicinereminder.data.repositories.MedicineReminderRepository
import com.example.medicinereminder.data.repositories.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Collections.emptyList
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val medicineRepository: MedicineRepository,
    private val doctorRepository: DoctorRepository,
    private val appointmentRepository: AppointmentRepository,
    private val medicineReminderRepository: MedicineReminderRepository
) : ViewModel() {

    val reminders: StateFlow<List<ReminderInfo>> = combine(
        medicineRepository.dailyMedicineReminders,
        doctorRepository.dailyRepository
    ){ medicines,doctors ->
        ReminderInfo.mergerReminders(doctors,medicines)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    fun updateTab(tab: HomeTab){
        _uiState.value = _uiState.value.copy(currentTab = tab)
    }
    fun updateReminder(reminder: ReminderInfo){
        _uiState.value = _uiState.value.copy(selectedReminder = reminder)
    }
    fun openBottomSheet(){
        _uiState.value = _uiState.value.copy(isBottomSheetShown = true)
    }
    fun closeBottomSheet(){
        _uiState.value = _uiState.value.copy(isBottomSheetShown = false)
    }

    fun deleteReminder(reminder: ReminderInfo){
        viewModelScope.launch(Dispatchers.IO) {
            when(reminder.reminder){
                is MedicineReminder -> medicineReminderRepository.deleteMedicineReminder(reminder.reminder)
                is Appointment -> appointmentRepository.deleteAppointment(reminder.reminder)
            }
        }
    }
    fun updateReminderState(reminder: ReminderInfo,newState: ReminderState){
        viewModelScope.launch(Dispatchers.IO) {
            when(reminder.reminder){
                is MedicineReminder -> {
                    val updatedMedicineReminder = reminder.reminder.copy(reminderState = newState)
                    medicineReminderRepository.updateMedicineReminder(updatedMedicineReminder)
                }
                is Appointment ->{
                    val updatedAppointment = reminder.reminder.copy(reminderState = newState)
                    appointmentRepository.updateAppointment(updatedAppointment)
                }
            }
        }
    }
}