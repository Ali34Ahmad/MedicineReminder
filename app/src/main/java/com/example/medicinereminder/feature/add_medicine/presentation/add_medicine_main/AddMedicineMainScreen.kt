package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.composables.ScreenIndicator
import com.example.medicinereminder.common.components.top_app_bar.StandardTopAppBarComponent
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.AddMedicineInfoIntent
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.AddMedicineInfoScreen
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.AddMedicineInfoViewModel
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_form.MedicineFormViewModel
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_information.MedicineInformationIntent
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_information.MedicineInformationViewModel
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_interaction_note.InteractionsAndNoteViewModel
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun AddMedicineMainScreen(modifier: Modifier = Modifier) {

    val addMedicineInfoViewModel: AddMedicineInfoViewModel = hiltViewModel()
    val addMedicineInfoUiState = addMedicineInfoViewModel.uiState.collectAsState().value

    val medicineInformationViewModel: MedicineInformationViewModel = hiltViewModel()
    val medicineInformationUiState = medicineInformationViewModel.uiState.collectAsState()

    val medicineFormViewModel: MedicineFormViewModel = hiltViewModel()
    val medicineFormUiState = medicineFormViewModel.uiState.collectAsState()

    val interactionsViewModel: InteractionsAndNoteViewModel = hiltViewModel()
    val interactionsUiState = interactionsViewModel.uiState.collectAsState()


    Scaffold(
        topBar = {
            Column {
                StandardTopAppBarComponent(
                    showTrailingIcon = false,
                    showNavigateUp = true,
                    title = R.string.add_medicine,
                )
                ScreenIndicator(
                    numberOfPages = addMedicineInfoUiState.numberOfIndicators,
                    currentPage = addMedicineInfoUiState.currentScreen,
                    modifier = Modifier.padding(
                        top = MaterialTheme.spacing.medium16,
                        bottom = MaterialTheme.spacing.small8
                    ),
                )
            }
        }
    ) { contentPadding ->
        Surface(modifier.padding(contentPadding)) {

            AddMedicineInfoScreen(
                medicineInformationUiState = medicineInformationUiState.value,
                onMedicineInformationIntent = medicineInformationViewModel::onIntent,
                interactionsUiState = interactionsUiState.value,
                onInteractionsIntent = interactionsViewModel::onIntent,
                medicineFormUiState = medicineFormUiState.value,
                onMedicineFormIntent = medicineFormViewModel::onIntent,
                onConfirmButtonClick = {
                    medicineInformationViewModel.onIntent(MedicineInformationIntent.ValidateData)
                    if (medicineInformationUiState.value.validInput)
                        addMedicineInfoViewModel.onIntent(AddMedicineInfoIntent.ConfirmButtonClick)
                },
                onCancelButtonClick = {},
            )
        }
    }
}