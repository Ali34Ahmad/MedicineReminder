package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_form

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.medicinereminder.R
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.components.ChooseMedicalFormComponent
import com.example.medicinereminder.common.components.dialog.DialogWithTextField
import com.example.medicinereminder.common.components.dialog.DialogWithTitleAndDescription
import com.example.medicinereminder.presentation.ui.constants.Icons

@Composable
fun ChooseMedicineFormSection(
    uiState: MedicineFormUiState,
    onIntent: (MedicineFormIntent)->Unit,
    modifier: Modifier = Modifier,
) {
    DialogWithTitleAndDescription(
        showDialog = uiState.showDeleteMedicineFormConfirmationDialog,
        description = R.string.delete_medicine_form_confirmation_desc,
        title = R.string.delete_medicine_form,
        confirmButtonText = R.string.delete,
        dismissButtonText = R.string.cancel,
        onDismissRequest = { onIntent(MedicineFormIntent.DismissDeleteMedicineFormConfirmationDialog) },
        onConfirmClick = { onIntent(MedicineFormIntent.DeleteMedicineFormConfirmationDialogConfirmButtonClick) }
    )
    DialogWithTextField(
        showDialog = uiState.showAddMedicineFormDialog,
        title = R.string.add_new_form,
        textFieldLabel = R.string.medicine_form,
        textFieldValue = uiState.medicineFormText,
        onValueChange = { onIntent(MedicineFormIntent.UpdateMedicineFormText(it)) },
        confirmButtonText = R.string.add,
        dismissButtonText = R.string.cancel,
        errorMessage = uiState.medicineFormErrorMessage,
        onDismissRequest = { onIntent(MedicineFormIntent.DismissAddMedicineFormDialog) },
        onConfirmClick = { onIntent(MedicineFormIntent.AddMedicineFormDialogConfirmButtonClick) })


    ChooseMedicalFormComponent(
        items = uiState.medicineForms,
        title = R.string.medicine_form,
        collapsedStateIcon = Icons.Outlined.Expand,
        expandedStateIcon = Icons.Outlined.Collapse,
        buttonTextExpandedState = R.string.show_more,
        buttonTextCollapsedState = R.string.show_less,
        selectedItemIndex = uiState.selectedPharmaceuticalFormIndex,
        onItemSelected = { onIntent(MedicineFormIntent.SelectMedicineForm(it)) },
        onItemDeleted = { onIntent(MedicineFormIntent.DeleteMedicineFormEvent(it)) },
        onAddNewFormClick = { onIntent(MedicineFormIntent.AddNewForm) },
        isExpanded = uiState.pharmaceuticalFormIsExpanded,
        onButtonClick = { onIntent(MedicineFormIntent.ExpandButtonClick) },
    )
}