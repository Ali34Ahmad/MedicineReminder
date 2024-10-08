package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_information

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.text_field.OutlinedTextFieldComponent
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun MedicineInfoSection(
    uiState: MedicineInformationUiState,
    onIntent: (MedicineInformationIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        OutlinedTextFieldComponent(
            value = uiState.medicineName,
            onValueChange = { onIntent(MedicineInformationIntent.UpdateMedicineName(it)) },
            label = R.string.medicine_name,
            errorMessage = uiState.medicineNameErrorMessage,
            isRequired = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = MaterialTheme.spacing.medium16,
                    start = MaterialTheme.spacing.medium16,
                    end = MaterialTheme.spacing.medium16,
                ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
            ),
        )
        OutlinedTextFieldComponent(
            value = uiState.quantity,
            onValueChange = { onIntent(MedicineInformationIntent.UpdateQuantity(it)) },
            label = R.string.quantity,
            isRequired = true,
            errorMessage = uiState.quantityErrorMessage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = MaterialTheme.spacing.medium16,
                    start = MaterialTheme.spacing.medium16,
                    end = MaterialTheme.spacing.medium16,
                ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
            ),
        )
        OutlinedTextFieldComponent(
            value = uiState.companyName,
            onValueChange = { onIntent(MedicineInformationIntent.UpdateCompanyName(it)) },
            label = R.string.company,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = MaterialTheme.spacing.medium16,
                    start = MaterialTheme.spacing.medium16,
                    end = MaterialTheme.spacing.medium16,
                ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
            ),
        )
    }
}