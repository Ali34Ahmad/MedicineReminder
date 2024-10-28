package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.intervals_program

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.list_item.ListItemWithSwitch
import com.example.medicinereminder.common.components.text_field.OutlinedTextFieldComponent
import com.example.medicinereminder.common.components.text_field.OutlinedTextFieldWithDropDownMenu
import com.example.medicinereminder.presentation.ui.constants.MenuItems
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun SetMedicationDurationSection(
    uiState: MedicationDurationSectionUiState,
    modifier: Modifier = Modifier,
    onIsForeverCheckChange: (Boolean) -> Unit,
    onMedicationDurationTextChange: (String) -> Unit,
    onExpandChange: (Boolean) -> Unit,
    onMedicationDurationUnitItemClick: (index:Int) -> Unit,
) {
    Column(
        modifier = modifier.verticalScroll(
            rememberScrollState()
        )
    ) {
        Text(
            text = stringResource(id = R.string.set_medication_duration),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.medium16,
                bottom = MaterialTheme.spacing.small8,
                start = MaterialTheme.spacing.medium16,
                end = MaterialTheme.spacing.medium16,
            )
        )
        ListItemWithSwitch(
            title = R.string.repeat_forever,
            checked = uiState.isRepeatForever,
            onCheckedChange = onIsForeverCheckChange,
            showLongDescription = true,
        )


        HorizontalDivider(
            thickness = MaterialTheme.spacing.small1,
            color = MaterialTheme.colorScheme.surfaceContainerLow,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium16,
                    vertical = MaterialTheme.spacing.medium16
                ),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
        ) {
            OutlinedTextFieldComponent(
                value = uiState.medicationDuration,
                label = R.string.duration,
                onValueChange = onMedicationDurationTextChange,
                isRequired = true,
                modifier = Modifier.weight(1f),
                enabled = !uiState.isRepeatForever,
                errorMessage = uiState.medicationDurationErrorMessage,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
            )
            OutlinedTextFieldWithDropDownMenu(
                value = stringResource(MenuItems.durationUnits()[uiState.selectedDurationUnitIndex].titleStringId),
                label = R.string.unit,
                isRequired = true,
                enabled = !uiState.isRepeatForever,
//                errorMessage = uiState.regularDoseAmountErrorMessage,
                modifier = Modifier.weight(1f),
                expanded = uiState.isDurationUnitMenuExpanded,
                onExpandChange = onExpandChange,
                menuItems = MenuItems.durationUnits(),
                onItemClick = onMedicationDurationUnitItemClick,
            )
        }
    }
}


data class MedicationDurationSectionUiState(
    val isRepeatForever: Boolean,
    val medicationDuration: String,
    val medicationDurationErrorMessage: Int?,
    val selectedDurationUnitIndex: Int,
    val isDurationUnitMenuExpanded: Boolean,
)

fun IntervalsProgramUiState.toProgramDurationUiState(): MedicationDurationSectionUiState {
    return MedicationDurationSectionUiState(
        isRepeatForever = this.isRepeatForever,
        medicationDuration=this.medicationDuration,
        medicationDurationErrorMessage=this.medicationDurationErrorMessage,
        selectedDurationUnitIndex=this.selectedMedicationDurationUnitIndex,
        isDurationUnitMenuExpanded=this.isMedicationDurationUnitMenuExpanded,
    )
}