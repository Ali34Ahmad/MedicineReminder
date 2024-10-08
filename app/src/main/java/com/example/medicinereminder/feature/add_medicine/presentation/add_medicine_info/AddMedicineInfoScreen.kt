package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.buttons.ButtonRow
import com.example.medicinereminder.data.local.medicineFormsFakes
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_form.ChooseMedicineFormSection
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_form.MedicineFormIntent
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_form.MedicineFormUiState
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_information.MedicineInfoSection
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_information.MedicineInformationIntent
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_information.MedicineInformationUiState
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_interaction_note.AddInteractionAndNoteSection
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_interaction_note.InteractionsAndNoteIntent
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_interaction_note.InteractionsAndNoteUiState
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.model.SelectableItem
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun AddMedicineInfoScreen(
    medicineInformationUiState: MedicineInformationUiState,
    onMedicineInformationIntent: (MedicineInformationIntent) -> Unit,
    interactionsUiState: InteractionsAndNoteUiState,
    onInteractionsIntent: (InteractionsAndNoteIntent) -> Unit,
    medicineFormUiState: MedicineFormUiState,
    onMedicineFormIntent: (MedicineFormIntent) -> Unit,
    onConfirmButtonClick:()->Unit,
    onCancelButtonClick:()->Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            ButtonRow(
                primaryButtonText = R.string.confirm,
                secondaryButtonText = R.string.cancel,
                onPrimaryButtonClick = onConfirmButtonClick,
                onSecondaryButtonClick = onCancelButtonClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { contentPadding ->
        Surface(modifier = modifier) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .verticalScroll(rememberScrollState()),
            ) {
                Column(
                    modifier = Modifier.padding(
                        top = MaterialTheme.spacing.large32,
                        bottom = MaterialTheme.spacing.medium16,
                    )
                ) {
                    MedicineInfoSection(
                        uiState = medicineInformationUiState,
                        onIntent = onMedicineInformationIntent
                    )
                    HorizontalDivider(
                        thickness = MaterialTheme.spacing.small8,
                        color = MaterialTheme.colorScheme.surfaceContainerLow,
                    )

                    AddInteractionAndNoteSection(
                        uiState=interactionsUiState,
                        onIntent = onInteractionsIntent,
                    )

                    HorizontalDivider(
                        thickness = MaterialTheme.spacing.small8,
                        color = MaterialTheme.colorScheme.surfaceContainerLow,
                    )
                    ChooseMedicineFormSection(
                        uiState=medicineFormUiState,
                        onIntent=onMedicineFormIntent,
                    )
                }
            }
        }
    }
}

fun List<String>.toSelectableItems(): List<SelectableItem> {
    return this.map { interaction ->
        SelectableItem(interaction, false)
    }
}


@DarkAndLightModePreview
@Composable
fun AddMedicineInfoScreenPreview() {
    MedicineReminderTheme {
        Surface {
            AddMedicineInfoScreen(
                medicineInformationUiState = MedicineInformationUiState(),
                onMedicineInformationIntent = {},
                interactionsUiState = InteractionsAndNoteUiState(),
                onInteractionsIntent = {},
                medicineFormUiState = MedicineFormUiState(
                    medicineForms = medicineFormsFakes,
                ),
                onMedicineFormIntent = {},
                onConfirmButtonClick = {},
                onCancelButtonClick = {},
            )
        }
    }
}