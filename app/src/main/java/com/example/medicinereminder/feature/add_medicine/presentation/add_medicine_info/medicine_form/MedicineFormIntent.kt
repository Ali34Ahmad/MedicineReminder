package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_form

import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_information.MedicineInformationIntent

sealed interface MedicineFormIntent{
    data class SelectMedicineForm(val index: Int) : MedicineFormIntent
    data object ExpandButtonClick : MedicineFormIntent

    data class DeleteMedicineFormEvent(val index: Int) : MedicineFormIntent
    data object DeleteMedicineFormConfirmationDialogConfirmButtonClick : MedicineFormIntent
    data object DismissDeleteMedicineFormConfirmationDialog : MedicineFormIntent

    data object AddNewForm : MedicineFormIntent
    data class UpdateMedicineFormText(val text: String) : MedicineFormIntent
    data object DismissAddMedicineFormDialog : MedicineFormIntent
    data object AddMedicineFormDialogConfirmButtonClick : MedicineFormIntent
}