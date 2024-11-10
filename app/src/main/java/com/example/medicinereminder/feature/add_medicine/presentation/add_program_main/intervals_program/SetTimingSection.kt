package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.intervals_program

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.text_field.OutlinedTextFieldComponent
import com.example.medicinereminder.common.components.time_picker.FlatTimePicker
import com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.day_specific_program.NavigateBackAndForwardRow
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing
import java.time.LocalTime


@Composable
fun SetTimingSection(
    uiState: TimingSectionUiState,
    onNonRegularAmountTextValueChange:(String)->Unit,
    onTimingBackButtonClick:()->Unit,
    onTimingNextButtonClick:()->Unit,
    modifier: Modifier = Modifier,
) {
//
//    val numberOfDosesPerDay = uiState.timesWithAmountInIntervalProgram[uiState.currentSelectedDayForTimingIndex
//        ?: 0].numberOfDosesPerDay.toInt()
    Column(
        modifier = modifier.verticalScroll(
            state = rememberScrollState()
        ),
    ) {
        Text(
            text = stringResource(id = R.string.set_dose_timing),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.medium16,
                bottom = MaterialTheme.spacing.small8,
                start = MaterialTheme.spacing.medium16,
                end = MaterialTheme.spacing.medium16,
            )
        )

        HorizontalDivider(
            thickness = MaterialTheme.spacing.small1,
            color = MaterialTheme.colorScheme.surfaceContainerLow,
        )
        if (!uiState.isRegularDose) {
            OutlinedTextFieldComponent(
                value = uiState.nonRegularDoseAmount,
                label = R.string.amount,
                onValueChange = onNonRegularAmountTextValueChange,
                isRequired = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.spacing.medium16,
                        end = MaterialTheme.spacing.medium16,
                        top = MaterialTheme.spacing.medium16,
                    ),
                errorMessage = uiState.nonRegularDoseAmountErrorMessage,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            FlatTimePicker(
                modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.large32)
                    .height(MaterialTheme.sizing.large132),
            )
        }
        NavigateBackAndForwardRow(
            onBackButtonClick = onTimingBackButtonClick,
            onNextButtonClick = onTimingNextButtonClick,
            doseOrder = uiState.currentDoseOrder + 1,
            totalDosesNumber = uiState.timesWithAmountInIntervalProgram.size,
            enableNextButton = true,
            enableBackButton = true,
        )
    }
}

data class TimingSectionUiState(
    val isRegularDose: Boolean,
    val nonRegularDoseAmount: String,
    val nonRegularDoseAmountErrorMessage: Int?,
    val currentDoseOrder: Int,
    val timesWithAmountInIntervalProgram: List<Pair<LocalTime, String>>,
)

fun IntervalsProgramUiState.toProgramTimingUiState(): TimingSectionUiState {
    return TimingSectionUiState(
        isRegularDose =this.isRegularDose,
        nonRegularDoseAmount =this.nonRegularDoseAmount,
        nonRegularDoseAmountErrorMessage =this.nonRegularDoseAmountErrorMessage,
        currentDoseOrder =this.currentDoseNumberSelectedForTiming,
        timesWithAmountInIntervalProgram =this.timesWithAmountInIntervalProgram,
    )
}