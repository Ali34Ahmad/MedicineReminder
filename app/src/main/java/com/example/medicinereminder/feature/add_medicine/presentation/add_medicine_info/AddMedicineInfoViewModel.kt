package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddMedicineInfoViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(AddMedicineInfoUiState())
    val uiState = _uiState.asStateFlow()

    fun onIntent(addMedicineInfoIntent: AddMedicineInfoIntent) {
        when (addMedicineInfoIntent) {
            AddMedicineInfoIntent.ConfirmButtonClick->moveScreenIndicatorForward()
        }
    }

    private fun moveScreenIndicatorForward(){
        if(uiState.value.currentScreen==uiState.value.numberOfIndicators) return
        _uiState.update {
            it.copy(currentScreen = it.currentScreen+1)
        }
    }

    fun moveScreenIndicatorBackward(){
        if(uiState.value.currentScreen==1) return
        _uiState.update {
            it.copy(currentScreen = it.currentScreen-1)
        }
    }
}
