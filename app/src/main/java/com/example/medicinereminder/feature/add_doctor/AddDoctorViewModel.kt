package com.example.medicinereminder.feature.add_doctor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.repositories.DoctorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddDoctorViewModel @Inject constructor(
    private val doctorRepository: DoctorRepository
): ViewModel() {
    private val _uiState = mutableStateOf(AddDoctorUIState())
    val uiState: State<AddDoctorUIState> = _uiState

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
            AddDoctorAction.Cancel -> TODO("use the shared viewModel to back to the previous page after saving the data")
            AddDoctorAction.Confirm -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val doctor = Doctor(
                        name = _uiState.value.doctorName,
                        address = _uiState.value.address,
                        phoneNumber = _uiState.value.phoneNumber,
                        specialty = _uiState.value.specialty,
                        imageFileName = "",
                        )
                    doctorRepository.addNewDoctor(doctor)
                }
                TODO("fields validation, and using the shared view model to update the current page indicator")
            }

            AddDoctorAction.NavigateUp -> TODO("not yet implemented")
        }
    }
}