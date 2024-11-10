package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.intervals_program

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.buttons.ButtonRow
import com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.day_specific_program.ProgramSection
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun IntervalsProgramScreen(
    uiState: IntervalsProgramUiState,
    onIntent: (IntervalsProgramIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val progress = when (uiState.currentSection) {
        ProgramSection.DOSES_AND_AMOUNT -> 0.33f
        ProgramSection.TIMING -> 0.66f
        ProgramSection.MEDICATION_DURATION -> 100f
    }
    val animateProgress = animateFloatAsState(
        targetValue = progress, label = "",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
            ButtonRow(
                primaryButtonText = R.string.confirm,
                secondaryButtonText = R.string.cancel,
                onPrimaryButtonClick = { onIntent(IntervalsProgramIntent.ConfirmButtonClick) },
                onSecondaryButtonClick = { onIntent(IntervalsProgramIntent.CancelButtonClick) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { contentPadding ->
        Surface(modifier = modifier.padding(contentPadding)) {
            Column(modifier = modifier) {
                LinearProgressIndicator(
                    progress = { animateProgress.value },
                    modifier = Modifier.fillMaxWidth(),
                )
                if (uiState.currentSection == ProgramSection.DOSES_AND_AMOUNT) {
                    ProgramInfoSection(
                        uiState = uiState.toProgramInfoSectionUiState(),
                        onRepeatEveryTextValueChange = {onIntent(IntervalsProgramIntent.UpdateRepeatEveryText(it))},
                        onExpandChange = {onIntent(IntervalsProgramIntent.UpdateRepetitionUnitMenuExpandedState(it))},
                        onRepetitionUnitItemClick = {onIntent(IntervalsProgramIntent.UpdateSelectedRepetitionUnitIndex(it))},
                        onRegularDoseCheckedChange = {onIntent(IntervalsProgramIntent.UpdateIsRegularDoseCheckedState(it))},
                        onAmountTextValueChange = {onIntent(IntervalsProgramIntent.UpdateAmountText(it))},
                        onNumberOfDosesPerDayTextValueChange = {onIntent(IntervalsProgramIntent.UpdateNumberOfDosesPerDayText(it))}
                    )
                }
                if (uiState.currentSection == ProgramSection.TIMING) {
                    SetTimingSection(
                        uiState = uiState.toProgramTimingUiState(),
                        onTimingNextButtonClick = {onIntent(IntervalsProgramIntent.TimingNextButtonClick)},
                        onTimingBackButtonClick = {onIntent(IntervalsProgramIntent.TimingBackButtonClick)},
                        onNonRegularAmountTextValueChange = {onIntent(IntervalsProgramIntent.UpdateNonRegularAmountTextValueChange(it))},
                    )
                }
                if (uiState.currentSection == ProgramSection.MEDICATION_DURATION) {
                    SetMedicationDurationSection(
                        uiState = uiState.toProgramDurationUiState(),
                        onIsForeverCheckChange={onIntent(IntervalsProgramIntent.UpdateIsRepeatForever(it))},
                        onMedicationDurationTextChange={onIntent(IntervalsProgramIntent.UpdateMedicationDurationText(it))},
                        onExpandChange = {onIntent(IntervalsProgramIntent.UpdateMedicationDurationUnitMenuExpandedState(it))},
                        onMedicationDurationUnitItemClick = {onIntent(IntervalsProgramIntent.UpdateSelectedMedicationDurationUnitIndex(it))},
                        )
                }
                HorizontalDivider(
                    thickness = MaterialTheme.spacing.small8,
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                )
            }
        }
    }
}