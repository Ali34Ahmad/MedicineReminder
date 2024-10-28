package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_information

import androidx.lifecycle.ViewModel
import com.example.medicinereminder.feature.add_medicine.domain.use_case.ValidateMedicineNameUseCase
import com.example.medicinereminder.feature.add_medicine.domain.use_case.ValidateMedicineQuantityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MedicineInformationViewModel @Inject constructor(
    private val validateMedicineNameUseCase: ValidateMedicineNameUseCase,
    private val validateMedicineQuantityUseCase: ValidateMedicineQuantityUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MedicineInformationUiState())
    val uiState = _uiState.asStateFlow()

    fun onIntent(medicineInformationIntent: MedicineInformationIntent) {
        when (medicineInformationIntent) {
            is MedicineInformationIntent.UpdateMedicineName -> updateMedicineName(
                medicineInformationIntent.name
            )

            is MedicineInformationIntent.UpdateQuantity -> updateQuantity(
                medicineInformationIntent.quantity
            )

            is MedicineInformationIntent.UpdateCompanyName -> updateCompanyName(
                medicineInformationIntent.companyName
            )

            is MedicineInformationIntent.ValidateData -> validateData()
        }
    }

    private fun updateMedicineName(value: String) {
        _uiState.update {
            it.copy(medicineName = value)
        }
    }

    private fun updateCompanyName(value: String) {
        _uiState.update {
            it.copy(companyName = value)
        }
    }

    private fun updateQuantity(value: String) {
        _uiState.update {
            it.copy(quantity = value)
        }
    }

    private fun validateData() {
        val medicineNameResult = validateMedicineNameUseCase(uiState.value.medicineName)
        val medicineQuantityResult = validateMedicineQuantityUseCase(uiState.value.quantity)
        val hasError = listOf(medicineNameResult, medicineQuantityResult)
            .any { it.errorMessage != null }
        if (!hasError) {
            _uiState.update {
                it.copy(
                    validInput = true,
                    medicineNameErrorMessage = null,
                    quantityErrorMessage = null
                )
            }
            return
        }
        _uiState.update {
            it.copy(
                medicineNameErrorMessage = medicineNameResult.errorMessage,
                quantityErrorMessage = medicineQuantityResult.errorMessage,
                validInput = false,
            )
        }
    }

}