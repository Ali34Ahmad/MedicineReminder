package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.day_specific_program

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.buttons.ButtonRow
import com.example.medicinereminder.common.components.buttons.IconPosition
import com.example.medicinereminder.common.components.buttons.TextButtonWithIcon
import com.example.medicinereminder.common.components.chip.InputChipWithThreeStates
import com.example.medicinereminder.common.components.list_item.ListItemWithSwitch
import com.example.medicinereminder.common.components.text_field.OutlinedTextFieldComponent
import com.example.medicinereminder.common.components.text_field.OutlinedTextFieldWithDropDownMenu
import com.example.medicinereminder.common.components.time_picker.FlatTimePicker
import com.example.medicinereminder.common.utility.extension.capitalizeFirstLetter
import com.example.medicinereminder.common.utility.extension.toShortName
import com.example.medicinereminder.presentation.ui.constants.ChipState
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.constants.MenuItems
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing
import java.time.DayOfWeek

@Composable
fun DaySpecificProgramScreen(
    uiState: DaySpecificProgramUiState,
    onIntent: (DaySpecificProgramIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            ButtonRow(
                primaryButtonText = R.string.confirm,
                secondaryButtonText = R.string.cancel,
                onPrimaryButtonClick = { onIntent(DaySpecificProgramIntent.ConfirmButtonClick) },
                onSecondaryButtonClick = { onIntent(DaySpecificProgramIntent.CancelButtonClick) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { contentPadding ->
        Surface(modifier = modifier.padding(contentPadding)) {
            Column(modifier = modifier) {
                LazyRow(
                    contentPadding = PaddingValues(MaterialTheme.spacing.medium16),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                ) {
                    items(uiState.daysOfWeekWithState.size) { index ->
                        InputChipWithThreeStates(
                            text = uiState.daysOfWeekWithState[index].dayOfWeek.toShortName(),
                            onClick = {
                                when (uiState.currentSection) {
                                    ProgramSection.DOSES_AND_AMOUNT -> onIntent(
                                        DaySpecificProgramIntent.UpdateSelectedDay(it)
                                    )

                                    ProgramSection.TIMING ->
                                        onIntent(
                                            DaySpecificProgramIntent.UpdateSelectedDayForTiming(
                                                it
                                            )
                                        )

                                    ProgramSection.MEDICATION_DURATION -> {}
                                }
                            },
                            chipState = uiState.daysOfWeekWithState[index].chipState,
                            clickable = true,
                            index = index,
                            isError = uiState.daysOfWeekWithState[index].isError,
                            longClickable = uiState.currentSection == ProgramSection.DOSES_AND_AMOUNT,
                            onLongClick = {
                                onIntent(
                                    DaySpecificProgramIntent.LongClickDayChip(
                                        it
                                    )
                                )
                            }
                        )
                    }
                }

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
                LinearProgressIndicator(
                    progress = { animateProgress.value },
                    modifier = Modifier.fillMaxWidth(),
                )

                if (uiState.currentSection == ProgramSection.DOSES_AND_AMOUNT) {
                    SetDosesNumberAndAmountSection(uiState = uiState, onIntent = onIntent)
                }
                if (uiState.currentSection == ProgramSection.TIMING) {
                    SetTimingSection(uiState = uiState, onIntent = onIntent)
                }
                if (uiState.currentSection == ProgramSection.MEDICATION_DURATION) {
                    SetMedicationDurationSection(uiState = uiState, onIntent = onIntent)
                }
                HorizontalDivider(
                    thickness = MaterialTheme.spacing.small8,
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                )
            }
        }
    }

}

@Composable
fun SetMedicationDurationSection(
    uiState: DaySpecificProgramUiState,
    onIntent: (DaySpecificProgramIntent) -> Unit,
    modifier: Modifier = Modifier,
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
            onCheckedChange = { onIntent(DaySpecificProgramIntent.UpdateIsRepeatForever) },
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
                onValueChange = {
                    onIntent(
                        DaySpecificProgramIntent.UpdateDoseDurationText(
                            it
                        )
                    )
                },
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
//                errorMessage = uiState.durationUnitErrorMessage,
                modifier = Modifier.weight(1f),
                expanded = uiState.isDurationUnitMenuExpanded,
                onExpandChange = {
                    onIntent(DaySpecificProgramIntent.UpdateDurationUnitsMenuExpandedState(it))
                },
                menuItems = MenuItems.durationUnits(),
                onItemClick = { onIntent(DaySpecificProgramIntent.UpdateSelectedDurationUnitIndex(it)) },
            )
        }
    }
}

@Composable
fun SetDosesNumberAndAmountSection(
    uiState: DaySpecificProgramUiState,
    onIntent: (DaySpecificProgramIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val enableDataInput =
        uiState.daysOfWeekWithState.firstOrNull { it.chipState == ChipState.SELECTED_WITH_ICON } != null
    val numberOfDosesPerDayImeAction = if (uiState.isRegularDose) ImeAction.Next else ImeAction.Done

    Column(
        modifier = modifier.verticalScroll(
            state = rememberScrollState()
        )
    ) {
        HorizontalDivider(
            thickness = MaterialTheme.spacing.small1,
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
            onCheckedChange = { onIntent(DaySpecificProgramIntent.UpdateIsRegularDose(it)) },
            showLongDescription = true,
            description = stringResource(id = R.string.regular_dose_desc),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium16,
                    vertical = MaterialTheme.spacing.medium16
                ),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
        ) {
            OutlinedTextFieldComponent(
                value = uiState.numberOfDosesPerDay,
                label = R.string.doses_per_day,
                enabled = enableDataInput,
                onValueChange = {
                    onIntent(
                        DaySpecificProgramIntent.UpdateNumberOfDosesPerDayText(
                            it
                        )
                    )
                },
                isRequired = true,
                errorMessage = uiState.numberOfDosesPerDayErrorMessage,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = numberOfDosesPerDayImeAction,
                    keyboardType = KeyboardType.Number
                ),
            )
            AnimatedVisibility(
                uiState.isRegularDose,
            ) {
                OutlinedTextFieldComponent(
                    value = uiState.regularDoseAmount,
                    label = R.string.amount,
                    enabled = enableDataInput,
                    onValueChange = {
                        onIntent(
                            DaySpecificProgramIntent.UpdateRegularDoseAmountText(
                                it
                            )
                        )
                    },
                    isRequired = true,
                    errorMessage = uiState.regularDoseAmountErrorMessage,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                )
            }
        }
    }
}

@Composable
fun SetTimingSection(
    uiState: DaySpecificProgramUiState,
    onIntent: (DaySpecificProgramIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val numberOfDosesPerDay = uiState.daysOfWeekWithState[uiState.currentSelectedDayForTimingIndex
        ?: 0].numberOfDosesPerDay.toInt()
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
        ListItemWithSwitch(
            title = R.string.set_unified_timing,
            description = stringResource(R.string.set_unified_timing_desc),
            checked = uiState.isUnifiedTimingForAllDays,
            onCheckedChange = {
                if (uiState.isRegularDose) onIntent(
                    DaySpecificProgramIntent.UpdateIsSameTimingForAllDays(
                        it
                    )
                )
            },
            showLongDescription = true,
        )

        HorizontalDivider(
            thickness = MaterialTheme.spacing.small1,
            color = MaterialTheme.colorScheme.surfaceContainerLow,
        )
        if (!uiState.isRegularDose) {
            OutlinedTextFieldComponent(
                value = uiState.nonRegularDoseAmount,
                label = R.string.amount,
                onValueChange = {
                    onIntent(
                        DaySpecificProgramIntent.UpdateNonRegularDoseAmount(
                            it
                        )
                    )
                },
                enabled = uiState.isTimingSectionEnabled,
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
            onBackButtonClick = { onIntent(DaySpecificProgramIntent.TimingsBackButtonClick) },
            onNextButtonClick = { onIntent(DaySpecificProgramIntent.TimingsNextButtonClick) },
            dayOfWeek = uiState.daysOfWeekWithState[uiState.currentSelectedDayForTimingIndex
                ?: 0].dayOfWeek,
            doseOrder = uiState.currentDoseOrder + 1,
            totalDosesNumber = numberOfDosesPerDay,
            enableNextButton = uiState.isTimingSectionEnabled && uiState.isNextButtonEnabled,
            enableBackButton = uiState.isTimingSectionEnabled && uiState.isBackButtonEnabled,
        )
    }
}


@Composable
fun NavigateBackAndForwardRow(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    dayOfWeek: DayOfWeek? = null,
    doseOrder: Int,
    totalDosesNumber: Int,
    enableNextButton: Boolean,
    enableBackButton: Boolean,
) {
    val dayOfWeekText = dayOfWeek?.name?.capitalizeFirstLetter() ?: ""
    Column(
        modifier = modifier
    ) {
        HorizontalDivider(
            thickness = MaterialTheme.spacing.small1,
            color = MaterialTheme.colorScheme.surfaceContainerLow,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium16),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButtonWithIcon(
                onClick = onBackButtonClick,
                text = R.string.back,
                icon = Icons.Outlined.ArrowBack,
                iconPosition = IconPosition.START,
                enabled = enableBackButton,
            )
            Text(
                text = if (enableBackButton && enableNextButton)
                    "$dayOfWeekText (${doseOrder}/${totalDosesNumber})"
                else "",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
            )
            TextButtonWithIcon(
                onClick = onNextButtonClick,
                text = R.string.next,
                icon = Icons.Outlined.ArrowForward,
                enabled = enableNextButton,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun DaySpecificProgramScreenPreview() {
    MedicineReminderTheme {
        Surface {
            DaySpecificProgramScreen(
                uiState = DaySpecificProgramUiState(
                    isRepeatForever = false,
                ),
                onIntent = {

                }
            )
        }
    }
}

/*
* isRegularDose=true---------------
* doses per day and amount are set the same for all days
* isRegularDose=true---------------
* doses per day and amount are set to each day (via data class)
* setUnifiedTiming=true
* the same timing for all days with the specified amount
* setUnifiedTiming=false
* the timing for each day with the specified amount
*  */