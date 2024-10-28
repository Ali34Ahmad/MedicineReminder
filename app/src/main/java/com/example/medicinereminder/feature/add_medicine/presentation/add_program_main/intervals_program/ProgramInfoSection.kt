package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.intervals_program

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.list_item.ListItemWithSwitch
import com.example.medicinereminder.common.components.text_field.OutlinedTextFieldComponent
import com.example.medicinereminder.common.components.text_field.OutlinedTextFieldWithDropDownMenu
import com.example.medicinereminder.presentation.ui.constants.MenuItems
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun ProgramInfoSection(
    uiState: ProgramInfoSectionUiState,
    onRepeatEveryTextValueChange: (String) -> Unit,
    onNumberOfDosesPerDayTextValueChange: (String) -> Unit,
    onExpandChange: (Boolean) -> Unit,
    onRegularDoseCheckedChange: (Boolean) -> Unit,
    onAmountTextValueChange: (String) -> Unit,
    onRepetitionUnitItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = MaterialTheme.spacing.medium24,
                    bottom = MaterialTheme.spacing.medium16,
                    start = MaterialTheme.spacing.medium16,
                    end = MaterialTheme.spacing.medium16
                ),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
        ) {
            OutlinedTextFieldComponent(
                value = uiState.repeatEveryText,
                onValueChange = onRepeatEveryTextValueChange,
                label = R.string.repeate_every,
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                errorMessage = uiState.repeatEveryTextErrorMessage,
            )
            OutlinedTextFieldWithDropDownMenu(
                value = stringResource(
                    MenuItems
                        .durationUnits()
                        [uiState.selectedRepetitionDurationUnitIndex]
                        .titleStringId
                ),
                onValueChange = {},
                label = R.string.unit,
                expanded = uiState.isRepetitionDurationUnitMenuExpanded,
                menuItems = MenuItems.durationUnits(),
                onExpandChange = onExpandChange,
                onItemClick = onRepetitionUnitItemClick,
                modifier = Modifier.weight(1f),
            )
        }
        HorizontalDivider(
            thickness = MaterialTheme.spacing.small8,
            color = MaterialTheme.colorScheme.surfaceContainerLow,
        )
        Text(
            text = stringResource(id = R.string.specify_amount_per_dose),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.medium16,
                bottom = MaterialTheme.spacing.small8,
                start = MaterialTheme.spacing.medium16,
                end = MaterialTheme.spacing.medium16,
            )
        )
        ListItemWithSwitch(
            title = R.string.regular_dose,
            checked = uiState.isRegularDose,
            onCheckedChange = onRegularDoseCheckedChange,
            showLongDescription = true,
            description = stringResource(id = R.string.regular_dose_desc),
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium24))
        OutlinedTextFieldComponent(
            value = uiState.numberOfDosesPerDayText,
            onValueChange = onNumberOfDosesPerDayTextValueChange,
            label = R.string.doses_per_day,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium16,
                ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            errorMessage = uiState.numberOfDosesPerDayTextErrorMessage,
        )
        if(uiState.isRegularDose){
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
            OutlinedTextFieldComponent(
                value = uiState.regularDoseAmountText,
                onValueChange = onAmountTextValueChange,
                label = R.string.amount,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.medium16,
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                errorMessage = uiState.regularDoseAmountErrorMessage,
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium24))
    }
}


data class ProgramInfoSectionUiState(
    val repeatEveryText: String ,
    val repeatEveryTextErrorMessage: Int?,
    val selectedRepetitionDurationUnitIndex: Int,
    val isRepetitionDurationUnitMenuExpanded: Boolean,

    val isRegularDose: Boolean ,
    val numberOfDosesPerDayText: String ,
    val numberOfDosesPerDayTextErrorMessage: Int? ,

    val regularDoseAmountText: String ,
    val regularDoseAmountErrorMessage: Int? ,
) {
}

fun IntervalsProgramUiState.toProgramInfoSectionUiState(): ProgramInfoSectionUiState {
    return ProgramInfoSectionUiState(
        repeatEveryText = this.repeatEveryText,
        repeatEveryTextErrorMessage = this.repeatEveryTextErrorMessage,
        selectedRepetitionDurationUnitIndex = this.selectedRepetitionDurationUnitIndex,
        isRepetitionDurationUnitMenuExpanded = this.isRepetitionDurationUnitMenuExpanded,
        isRegularDose = this.isRegularDose,
        numberOfDosesPerDayText = this.numberOfDosesPerDayText,
        numberOfDosesPerDayTextErrorMessage = this.numberOfDosesPerDayErrorMessage,
        regularDoseAmountText = this.regularDoseAmountText,
        regularDoseAmountErrorMessage = this.regularDoseAmountErrorMessage
    )
}

@DarkAndLightModePreview
@Composable
fun ProgramInfoSectionPreview() {
    MedicineReminderTheme {
        Surface {
            ProgramInfoSection(
                uiState = ProgramInfoSectionUiState(
                    repeatEveryText = "",
                    repeatEveryTextErrorMessage = null,
                    selectedRepetitionDurationUnitIndex = 0,
                    isRepetitionDurationUnitMenuExpanded = false,
                    isRegularDose = true,
                    numberOfDosesPerDayText = "",
                    numberOfDosesPerDayTextErrorMessage = null,
                    regularDoseAmountText = "",
                    regularDoseAmountErrorMessage = null
                ),
                onExpandChange = {},
                onAmountTextValueChange = {},
                onRepetitionUnitItemClick = {},
                onRepeatEveryTextValueChange = {},
                onNumberOfDosesPerDayTextValueChange = {},
                onRegularDoseCheckedChange = {},
            )
        }
    }
}