package com.example.medicinereminder.feature.homeScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.data.model.ReminderInfo
import com.example.medicinereminder.data.repositories.DoctorRepository
import com.example.medicinereminder.data.repositories.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import java.util.Collections.emptyList
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    medicineRepository: MedicineRepository,
    doctorRepository: DoctorRepository
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

    private val _currentTab = MutableStateFlow<HomeTab>(HomeTab.ALL)
    val currentTab: StateFlow<HomeTab> = _currentTab.asStateFlow()

    fun updateTab(tab: HomeTab){
        _currentTab.value = tab
    }

}