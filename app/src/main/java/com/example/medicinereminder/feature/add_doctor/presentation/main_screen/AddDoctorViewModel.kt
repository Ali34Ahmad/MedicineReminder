package com.example.medicinereminder.feature.add_doctor.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.common.utility.extension.doesMatchSearchQuery
import com.example.medicinereminder.data.local.doctor1
import com.example.medicinereminder.data.local.doctor2
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.domain.usecase.doctor_use_cases.AddDoctorUseCase
import com.example.medicinereminder.domain.usecase.doctor_use_cases.GetAllDoctorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddDoctorViewModel @Inject constructor(
    private val addDoctorUseCase: AddDoctorUseCase,
    private val getAllDoctorsUseCase: GetAllDoctorsUseCase
): ViewModel() {
    private val _uiState = mutableStateOf(AddDoctorUIState())
    val uiState: State<AddDoctorUIState> = _uiState

    private val x  = flowOf(listOf(doctor1, doctor2))
    private val doctorName = snapshotFlow { uiState.value.doctorName }
    @OptIn(FlowPreview::class)
    val doctors = doctorName.debounce(500).combine(x){ doctorName, doctors ->
        if(doctorName.isBlank()){
            doctors
        }else{
            doctors.filter {
                it.doesMatchSearchQuery(doctorName)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = emptyList(),
        started = SharingStarted.WhileSubscribed(5000)
    )
    fun onAction(action: AddDoctorAction){
        when(action){
            is AddDoctorAction.UpdateDoctorName -> {
                _uiState.value = _uiState.value.copy(doctorName = action.name )
            }
            is AddDoctorAction.UpdateSpecialty -> {
                _uiState.value = _uiState.value.copy(specialty = action.specialty)
            }
            is AddDoctorAction.UpdatePhoneNumber -> {
                _uiState.value = _uiState.value.copy(phoneNumber = action.phoneNumber)
            }
            is AddDoctorAction.UpdateCity -> {
                val updatedAddress = _uiState.value.address.copy(city = action.city)
                _uiState.value = _uiState.value.copy(address = updatedAddress)
            }
            is AddDoctorAction.UpdateNote -> {
                _uiState.value = _uiState.value.copy(note = action.note)
            }
            is AddDoctorAction.UpdateStateOrGovernment -> {
                val updatedAddress = _uiState.value.address.copy(stateOrGovernorate = action.stateOrGovernment)
                _uiState.value = _uiState.value.copy(address = updatedAddress)
            }
            is AddDoctorAction.UpdateStreet -> {
                val updatedAddress = _uiState.value.address.copy(street = action.street)
                _uiState.value = _uiState.value.copy(address = updatedAddress)
            }
            AddDoctorAction.Cancel -> {
                TODO("use the shared viewModel to back to the previous page after saving the data")
            }
            AddDoctorAction.Confirm -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val doctor = Doctor(
                        name = _uiState.value.doctorName,
                        specialty = _uiState.value.specialty,
                        phoneNumber = _uiState.value.phoneNumber,
                        address = _uiState.value.address,
                        )
                    addDoctorUseCase(doctor)
                }
                TODO("fields validation, and using the shared view model or any thing else,and updating the current page indicator")
            }

            AddDoctorAction.NavigateUp -> {
                TODO("not yet implemented, need saving data into some thing")
            }
            AddDoctorAction.HideAddressInfo -> {
                _uiState.value = _uiState.value.copy(isAddressInfoShown = false)
            }
            AddDoctorAction.ShowAddressInfo -> {
                _uiState.value = _uiState.value.copy(isAddressInfoShown = true)
            }
            AddDoctorAction.HideContactInfo -> {
                _uiState.value = _uiState.value.copy(isContactInfoShown = false)
            }
            AddDoctorAction.ShowContactInfo -> {
                _uiState.value = _uiState.value.copy(isContactInfoShown = true)
            }
            AddDoctorAction.HideExistingDoctorsMenu -> {
                _uiState.value = _uiState.value.copy(isExistingDoctorsMenuShown = false)
            }
            AddDoctorAction.ShowExistingDoctorsMenu -> {
                _uiState.value = _uiState.value.copy(isExistingDoctorsMenuShown = true)
            }

            AddDoctorAction.ShowBottomSheet -> {
                _uiState.value = _uiState.value.copy(isBottomSheetShown = true)
            }
            AddDoctorAction.HideBottomSheet -> {
                _uiState.value = _uiState.value.copy(isBottomSheetShown = false)
            }
            is AddDoctorAction.AddExistingDoctor -> TODO("need the medicine info")
        }
    }
}