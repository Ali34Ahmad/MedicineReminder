package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.intervals_program

import androidx.compose.ui.graphics.Paint
import androidx.lifecycle.ViewModel
import com.example.medicinereminder.feature.add_medicine.domain.use_case.ValidateMedicineAmountUseCase
import com.example.medicinereminder.feature.add_medicine.domain.use_case.ValidateNumberOfDosesUseCase
import com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.day_specific_program.ProgramSection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class IntervalsProgramViewModel @Inject constructor(
    private val validateNumberOfDosesUseCase: ValidateNumberOfDosesUseCase,
    private val validateMedicineAmountUseCase: ValidateMedicineAmountUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(IntervalsProgramUiState())
    val uiState = _uiState.asStateFlow()

    fun onIntent(intent: IntervalsProgramIntent) {
        when (intent) {
            is IntervalsProgramIntent.UpdateRepeatEveryText -> updateRepeatEveryText(intent.text)
            is IntervalsProgramIntent.UpdateSelectedRepetitionUnitIndex -> updateSelectedRepetitionUnit(
                intent.index
            )

            is IntervalsProgramIntent.UpdateRepetitionUnitMenuExpandedState -> updateRepetitionUnitMenuExpandedState(
                intent.expanded
            )

            is IntervalsProgramIntent.UpdateIsRegularDoseCheckedState -> updateIsRegularDoseCheckedState(
                intent.checked
            )

            is IntervalsProgramIntent.UpdateAmountText -> updateAmountText(intent.text)
            is IntervalsProgramIntent.UpdateNumberOfDosesPerDayText -> updateNumberOfDosesPerDayText(
                intent.text
            )

            is IntervalsProgramIntent.UpdateNonRegularAmountTextValueChange -> updateNonRegularAmountText(
                intent.text
            )

            is IntervalsProgramIntent.UpdateIsRepeatForever -> updateIsRepeatForever(intent.checked)
            is IntervalsProgramIntent.UpdateMedicationDurationText -> updateMedicationDurationText(
                intent.text
            )

            is IntervalsProgramIntent.UpdateMedicationDurationUnitMenuExpandedState -> updateMedicationDurationUnitMenuExpandedState(
                intent.expanded
            )

            is IntervalsProgramIntent.UpdateSelectedMedicationDurationUnitIndex -> updateSelectedMedicationDurationUnitIndex(
                intent.index
            )

            is IntervalsProgramIntent.TimingNextButtonClick -> moveCurrentSelectedDoseOrderForward()
            is IntervalsProgramIntent.TimingBackButtonClick -> moveCurrentSelectedDoseOrderBackward()

            is IntervalsProgramIntent.ConfirmButtonClick -> onConfirmButtonClick()
            is IntervalsProgramIntent.CancelButtonClick -> onCancelButtonClick()
        }
    }

    private fun updateSelectedMedicationDurationUnitIndex(index: Int) {
        _uiState.update { it.copy(selectedMedicationDurationUnitIndex = index) }
    }

    private fun updateMedicationDurationUnitMenuExpandedState(expanded: Boolean) {
        _uiState.update { it.copy(isMedicationDurationUnitMenuExpanded = expanded) }
    }

    private fun updateMedicationDurationText(text: String) {
        _uiState.update { it.copy(medicationDuration = text) }
    }

    private fun updateIsRepeatForever(checked: Boolean) {
        _uiState.update { it.copy(isRepeatForever = checked) }
    }

    private fun moveCurrentSelectedDoseOrderForward() {
        if (isMovingForwardNotValid()) return
        _uiState.update {
            it.copy(
                currentDoseNumberSelectedForTiming = it.currentDoseNumberSelectedForTiming + 1,
            )
        }
        updateNonRegularAmountText(getNonRegularDoseAmount())
    }

    private fun getNonRegularDoseAmount(): String {
        return uiState.value.timesWithAmountInIntervalProgram[uiState.value.currentDoseNumberSelectedForTiming].second
    }

    private fun isMovingForwardNotValid(): Boolean {
        val lastTimingIndex = uiState.value.timesWithAmountInIntervalProgram.lastIndex
        return uiState.value.currentDoseNumberSelectedForTiming == lastTimingIndex
    }

    private fun onConfirmButtonClick() {
        if (uiState.value.currentSection == ProgramSection.DOSES_AND_AMOUNT) {
            checkNumberOfDosesPerDay()
            checkRegularAmountText()
        }
        if (uiState.value.currentSection == ProgramSection.TIMING) {
            checkAmountForAllTimes()
        }
        if (uiState.value.numberOfDosesPerDayErrorMessage != null
            || uiState.value.regularDoseAmountErrorMessage != null
            || uiState.value.nonRegularDoseAmountErrorMessage != null
        ) return
        when (uiState.value.currentSection) {
            ProgramSection.DOSES_AND_AMOUNT -> {
                setCurrentSectionTo(ProgramSection.TIMING)
                createTimingList()
            }

            ProgramSection.TIMING -> {
                setCurrentSectionTo(ProgramSection.MEDICATION_DURATION)
            }

            ProgramSection.MEDICATION_DURATION -> {}
        }
    }

    private fun onCancelButtonClick() {

    }

    private fun createTimingList() {
        _uiState.update {
            it.copy(
                timesWithAmountInIntervalProgram = createListOfTimingsWithTheSize(
                    uiState.value.numberOfDosesPerDayText.toInt()
                )
            )
        }
    }

    private fun createListOfTimingsWithTheSize(size: Int): List<Pair<LocalTime, String>> {
        val timings = mutableListOf<Pair<LocalTime, String>>()
        for (i in 0..<size)
            timings.add(
                LocalTime.now() to uiState.value.regularDoseAmountText
            )
        return timings.toList()
    }

    private fun setCurrentSectionTo(section: ProgramSection) {
        _uiState.update { it.copy(currentSection = section) }
    }

    private fun checkAmountForAllTimes() {
        uiState.value.timesWithAmountInIntervalProgram.forEachIndexed { timingIndex, timing ->
            val amountValidationResult = validateMedicineAmountUseCase(timing.second)

            if (amountValidationResult.errorMessage == null) {
                _uiState.update {
                    it.copy(
                        nonRegularDoseAmountErrorMessage = null,
                    )
                }
            } else {
                updateCurrentDoseOrder(timingIndex)

                _uiState.update {
                    it.copy(
                        nonRegularDoseAmountErrorMessage = amountValidationResult.errorMessage,
                        nonRegularDoseAmount = it.timesWithAmountInIntervalProgram[timingIndex].second,
                    )
                }
                return
            }
        }
    }

    private fun updateCurrentDoseOrder(order: Int) {
        _uiState.update {
            it.copy(
                currentDoseNumberSelectedForTiming = order,
            )
        }
    }

    private fun checkRegularAmountText() {
        if (!uiState.value.isRegularDose) return
        val amountValidationResult =
            validateMedicineAmountUseCase(uiState.value.regularDoseAmountText)
        _uiState.update { it.copy(regularDoseAmountErrorMessage = amountValidationResult.errorMessage) }
    }

    private fun checkNumberOfDosesPerDay() {
        val validationResult = validateNumberOfDosesUseCase(uiState.value.numberOfDosesPerDayText)
        _uiState.update { it.copy(numberOfDosesPerDayErrorMessage = validationResult.errorMessage) }
    }


    private fun moveCurrentSelectedDoseOrderBackward() {
        if (isMovingBackwardNotValid()) return
        _uiState.update {
            it.copy(
                currentDoseNumberSelectedForTiming = it.currentDoseNumberSelectedForTiming - 1,
            )
        }
        updateNonRegularAmountText(getNonRegularDoseAmount())
    }

    private fun isMovingBackwardNotValid(): Boolean {
        return uiState.value.currentDoseNumberSelectedForTiming == 0
    }

    private fun updateIsRegularDoseCheckedState(checked: Boolean) {
        _uiState.update {
            it.copy(
                isRegularDose = checked,
                regularDoseAmountText = "",
            )
        }
    }

    private fun updateRepetitionUnitMenuExpandedState(expanded: Boolean) {
        _uiState.update { it.copy(isRepetitionDurationUnitMenuExpanded = expanded) }
    }

    private fun updateRepeatEveryText(text: String) {
        _uiState.update { it.copy(repeatEveryText = text) }
    }

    private fun updateSelectedRepetitionUnit(index: Int) {
        _uiState.update { it.copy(selectedRepetitionDurationUnitIndex = index) }
    }

    private fun updateNumberOfDosesPerDayText(text: String) {
        _uiState.update { it.copy(numberOfDosesPerDayText = text) }
    }

    private fun updateAmountText(text: String) {
        _uiState.update { it.copy(regularDoseAmountText = text) }
    }

    private fun updateNonRegularAmountText(text: String) {
        _uiState.update { it.copy(nonRegularDoseAmount = text) }
        updateAmountAtTimingIndex(uiState.value.currentDoseNumberSelectedForTiming)
    }

    private fun updateAmountAtTimingIndex(currentIndex: Int) {
        _uiState.update {
            it.copy(
                timesWithAmountInIntervalProgram = uiState.value.timesWithAmountInIntervalProgram.mapIndexed { index, timeAndAmount ->
                    if (currentIndex == index) {
                        timeAndAmount.first to uiState.value.nonRegularDoseAmount
                    } else {
                        timeAndAmount
                    }
                }
            )
        }
    }

}