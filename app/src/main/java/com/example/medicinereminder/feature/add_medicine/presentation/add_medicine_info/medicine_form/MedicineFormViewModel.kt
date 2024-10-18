package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.data.local.entity.MedicineForm
import com.example.medicinereminder.feature.add_medicine.domain.use_case.AddMedicineFormUseCase
import com.example.medicinereminder.feature.add_medicine.domain.use_case.DeleteMedicineFormUseCase
import com.example.medicinereminder.feature.add_medicine.domain.use_case.GetMedicineFormsUseCase
import com.example.medicinereminder.feature.add_medicine.domain.use_case.ValidateMedicineFormNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineFormViewModel @Inject constructor(
    private val getMedicineFormsUseCase: GetMedicineFormsUseCase,
    private val addMedicineFormUseCase: AddMedicineFormUseCase,
    private val deleteMedicineFormUseCase: DeleteMedicineFormUseCase,
    private val validateMedicineFormNameUseCase: ValidateMedicineFormNameUseCase,
):ViewModel() {
    private val _uiState = MutableStateFlow(MedicineFormUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getMedicineForms()
    }

    fun onIntent(medicineFormIntent: MedicineFormIntent){
        when(medicineFormIntent){
            is MedicineFormIntent.SelectMedicineForm -> updateSelectedMedicineFormIndex(
            medicineFormIntent.index
            )
            is MedicineFormIntent.DeleteMedicineFormEvent -> deletePharmaceuticalFormEvent(medicineFormIntent.index)
            is MedicineFormIntent.DeleteMedicineFormConfirmationDialogConfirmButtonClick -> deleteMedicineFormConfirmationDialogConfirmEvent()
            is MedicineFormIntent.DismissDeleteMedicineFormConfirmationDialog -> deleteMedicineFormConfirmationDialogDismissEvent()

            is MedicineFormIntent.AddNewForm -> addMedicineFormEvent()
            is MedicineFormIntent.UpdateMedicineFormText -> updateMedicineFormText(medicineFormIntent.text)
            is MedicineFormIntent.DismissAddMedicineFormDialog -> addMedicineFormDialogDismissEvent()
            is MedicineFormIntent.AddMedicineFormDialogConfirmButtonClick -> addMedicineFormDialogConfirmEvent()

            is MedicineFormIntent.ExpandButtonClick -> updateExpandedState()
        }
    }

    private fun getMedicineForms(){
        getMedicineFormsUseCase().onEach {medicineForms->
            _uiState.update { it.copy(medicineForms=medicineForms) }
        }.launchIn(viewModelScope)
    }

    private fun updateSelectedMedicineFormIndex(index: Int) {
        _uiState.update {
            it.copy(selectedPharmaceuticalFormIndex = index)
        }
    }

    private fun updateExpandedState() {
        _uiState.update {
            it.copy(pharmaceuticalFormIsExpanded = !it.pharmaceuticalFormIsExpanded)
        }
    }

    private fun deletePharmaceuticalFormEvent(index: Int){
        _uiState.update {
            it.copy(
                showDeleteMedicineFormConfirmationDialog = true,
                medicineFormToDeleteIndex = index,
            )
        }
    }

    private fun deleteMedicineFormConfirmationDialogConfirmEvent(){
        if(uiState.value.medicineFormToDeleteIndex==null) return
        viewModelScope.launch {
            deleteMedicineFormUseCase(uiState.value.medicineForms[uiState.value.medicineFormToDeleteIndex!!])
            _uiState.update {
                it.copy(
                    showDeleteMedicineFormConfirmationDialog = false,
                    medicineFormToDeleteIndex = null,
                )
            }
        }
    }
    private fun deleteMedicineFormConfirmationDialogDismissEvent(){
        _uiState.update{
            it.copy(
                showDeleteMedicineFormConfirmationDialog = false,
                medicineFormToDeleteIndex = null,
            )
        }
    }
    private fun addMedicineFormEvent(){
        _uiState.update {
            it.copy(
                showAddMedicineFormDialog = true,
            )
        }
    }

    private fun updateMedicineFormText(value: String){
        _uiState.update {
            it.copy(
                medicineFormText = value,
            )
        }
    }

    private fun addMedicineFormDialogConfirmEvent(){
        val medicineFormNameResult=validateMedicineFormNameUseCase(uiState.value.medicineFormText)
        if (medicineFormNameResult.errorMessage!=null){
            _uiState.update {
                it.copy(
                    medicineFormErrorMessage=medicineFormNameResult.errorMessage
                )
            }
            return
        }
        viewModelScope.launch {
            addMedicineFormUseCase(
                MedicineForm(
                    name = uiState.value.medicineFormText,
                    isAddedByUser = true,
                )
            )
            _uiState.update {
                it.copy(
                    showAddMedicineFormDialog = false,
                    medicineFormText = "",
                )
            }
        }
    }

    private fun addMedicineFormDialogDismissEvent(){
        _uiState.update{
            it.copy(
                showAddMedicineFormDialog = false,
                medicineFormText = "",
            )
        }
    }
}