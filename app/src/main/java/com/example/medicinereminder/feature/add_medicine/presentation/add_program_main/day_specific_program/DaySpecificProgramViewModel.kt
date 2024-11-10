package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.day_specific_program

import androidx.lifecycle.ViewModel
import com.example.medicinereminder.feature.add_medicine.domain.use_case.ValidateMedicineAmountUseCase
import com.example.medicinereminder.feature.add_medicine.domain.use_case.ValidateNumberOfDosesUseCase
import com.example.medicinereminder.feature.add_medicine.domain.use_case.ValidationResult
import com.example.medicinereminder.presentation.ui.constants.ChipState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class DaySpecificProgramViewModel @Inject constructor(
    private val validateNumberOfDosesUseCase: ValidateNumberOfDosesUseCase,
    private val validateMedicineAmountUseCase: ValidateMedicineAmountUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DaySpecificProgramUiState())
    val uiState = _uiState.asStateFlow()

    fun onIntent(daySpecificProgramIntent: DaySpecificProgramIntent) {
        when (daySpecificProgramIntent) {
            is DaySpecificProgramIntent.UpdateSelectedDay -> updateClickedDaySelectionState(
                daySpecificProgramIntent.index
            )

            is DaySpecificProgramIntent.LongClickDayChip -> updateLongClickedDaySelectionState(
                daySpecificProgramIntent.index
            )

            is DaySpecificProgramIntent.UpdateIsRegularDose -> updateIsRegularDose(
                daySpecificProgramIntent.checked
            )

            is DaySpecificProgramIntent.UpdateNumberOfDosesPerDayText -> updateNumberOfDosesPerDayText(
                daySpecificProgramIntent.text
            )

            is DaySpecificProgramIntent.UpdateRegularDoseAmountText -> updateRegularDoseAmountText(
                daySpecificProgramIntent.text
            )

            is DaySpecificProgramIntent.UpdateIsSameTimingForAllDays -> updateIsSameTimingForAllDay(
                daySpecificProgramIntent.checked
            )

            is DaySpecificProgramIntent.UpdateSelectedDayForTiming -> updateClickedDaySelectionStateInTimingSection(
                daySpecificProgramIntent.index
            )

            is DaySpecificProgramIntent.UpdateNonRegularDoseAmount -> updateNonRegularDoseAmountText(
                daySpecificProgramIntent.text
            )

            is DaySpecificProgramIntent.TimingsBackButtonClick -> moveCurrentSelectedDayAndDoseOrderBackward()
            is DaySpecificProgramIntent.TimingsNextButtonClick -> moveCurrentSelectedDayAndDoseOrderForward()

            is DaySpecificProgramIntent.UpdateIsRepeatForever -> updateIsRepeatForever()
            is DaySpecificProgramIntent.UpdateDoseDurationText -> updateDoseDurationText(
                daySpecificProgramIntent.text
            )

            is DaySpecificProgramIntent.UpdateSelectedDurationUnitIndex -> updateSelectedDurationUnitIndex(
                daySpecificProgramIntent.index
            )

            is DaySpecificProgramIntent.UpdateDurationUnitsMenuExpandedState -> updateDurationUnitsMenuExpandedState(
                daySpecificProgramIntent.expanded
            )


            DaySpecificProgramIntent.UpdateDoseDurationUnit -> {}
            DaySpecificProgramIntent.ConfirmButtonClick -> onConfirmButtonClick()
            DaySpecificProgramIntent.CancelButtonClick -> {}
        }
    }

    private fun updateClickedDaySelectionState(index: Int) {
        val dayOfWeekWithChipState = uiState.value.daysOfWeekWithState[index]
        if (dayOfWeekWithChipState.chipState == ChipState.SELECTED) {
            changeDayState(index, ChipState.UNSELECTED)
            return
        } else if (dayOfWeekWithChipState.chipState == ChipState.UNSELECTED) {
            if (uiState.value.isRegularDose) {
                changeDayState(index, ChipState.SELECTED_WITH_ICON)
            } else {
                changeDayState(index, ChipState.SELECTED)
            }
            return
        }
        if (uiState.value.isRegularDose) {
            changeDayState(index, ChipState.UNSELECTED)
        } else {
            changeDayState(index, ChipState.SELECTED)
        }
    }

    private fun changeDayState(index: Int, chipState: ChipState) {
        _uiState.update {
            it.copy(
                daysOfWeekWithState = it.daysOfWeekWithState.mapIndexed { currentIndex, dayOfWeekWithChipState ->
                    if (currentIndex == index) dayOfWeekWithChipState.copy(chipState = chipState)
                    else dayOfWeekWithChipState
                }.toMutableList()
            )
        }
    }

    private fun changeDayChipErrorState(index: Int, isError: Boolean) {
        _uiState.update {
            it.copy(
                daysOfWeekWithState = it.daysOfWeekWithState.mapIndexed { currentIndex, dayOfWeekWithChipState ->
                    if (currentIndex == index) dayOfWeekWithChipState.copy(isError = isError)
                    else dayOfWeekWithChipState
                }.toMutableList()
            )
        }
    }

    private fun changeNumberOfDosesDay(index: Int, numberOfDoses: String) {
        _uiState.update {
            it.copy(
                daysOfWeekWithState = it.daysOfWeekWithState.mapIndexed { currentIndex, dayOfWeekWithChipState ->
                    if (currentIndex == index) dayOfWeekWithChipState.copy(numberOfDosesPerDay = numberOfDoses)
                    else dayOfWeekWithChipState
                }.toMutableList()
            )
        }
    }


    private fun updateLongClickedDaySelectionState(index: Int) {
        val dayOfWeek = uiState.value.daysOfWeekWithState[index]
        if (dayOfWeek.chipState == ChipState.SELECTED) {
            changeDayState(index, ChipState.SELECTED_WITH_ICON)
            clearSelectionWithIconForAllDaysExcept(index)
            updateNumberOfDosesPerDayText(uiState.value.daysOfWeekWithState[index].numberOfDosesPerDay)
            return
        }
        if (dayOfWeek.chipState == ChipState.UNSELECTED) {
            changeDayState(index, ChipState.SELECTED_WITH_ICON)
            clearSelectionWithIconForAllDaysExcept(index)
            updateNumberOfDosesPerDayText(uiState.value.daysOfWeekWithState[index].numberOfDosesPerDay)
            return
        }
        changeDayState(index, ChipState.UNSELECTED)
    }

    private fun updateIsRegularDose(value: Boolean) {
        _uiState.update { it.copy(isRegularDose = value) }
        if (uiState.value.isRegularDose) {
            setSelectedWithIconStateForSelectedDays()
            updateIsSameTimingForAllDay(true)
        } else {
            clearSelectionWithIconForAllDaysExcept(getFirstSelectedOrSelectedWithIconDayIndex())
            clearNumberOfDosesInAllDaysExceptFirstSelectedWithIcon()
            updateIsSameTimingForAllDay(false)
        }
    }

    private fun clearNumberOfDosesInAllDaysExceptFirstSelectedWithIcon() {
        val firstSelectedWithIcon = getFirstSelectedWithIconDayIndex()
        _uiState.update {
            it.copy(
                daysOfWeekWithState = it.daysOfWeekWithState.mapIndexed { index, dayOfWeekWithChipState ->
                    if (firstSelectedWithIcon != index) dayOfWeekWithChipState.copy(
                        numberOfDosesPerDay = ""
                    )
                    else dayOfWeekWithChipState
                }.toMutableList()
            )
        }
    }

    private fun getFirstSelectedOrSelectedWithIconDayIndex(): Int {
        val index =
            uiState.value.daysOfWeekWithState.indexOfFirst { it.chipState == ChipState.SELECTED || it.chipState == ChipState.SELECTED_WITH_ICON }
        return index
    }

    private fun getFirstSelectedWithIconDayIndex(): Int {
        val index =
            uiState.value.daysOfWeekWithState.indexOfFirst { it.chipState == ChipState.SELECTED_WITH_ICON }
        return index
    }


    private fun setSelectedWithIconStateForSelectedDays() {
        _uiState.update {
            it.copy(
                daysOfWeekWithState = it.daysOfWeekWithState.map { dayOfWeekWithChipState ->
                    val currentState = dayOfWeekWithChipState.chipState
                    if (currentState == ChipState.SELECTED) {
                        dayOfWeekWithChipState.copy(chipState = ChipState.SELECTED_WITH_ICON)
                    } else {
                        dayOfWeekWithChipState
                    }
                }.toMutableList()
            )
        }
    }

    private fun updateNumberOfDosesPerDayTextAtIndices(indices: List<Int>) {
        indices.forEach {
            changeNumberOfDosesDay(
                index = it,
                numberOfDoses = uiState.value.numberOfDosesPerDay
            )
        }
    }

    private fun updateNumberOfDosesPerDayText(value: String) {
        _uiState.update {
            it.copy(
                numberOfDosesPerDay = value,
            )
        }
        val selectedWithIconIndices = getAllSelectedWithIconDaysIndices()
        updateNumberOfDosesPerDayTextAtIndices(selectedWithIconIndices)
    }

    private fun getAllSelectedWithIconDaysIndices(): List<Int> {
        val indices = mutableListOf<Int>()
        uiState.value.daysOfWeekWithState.forEachIndexed { index, dayOfWeekWithChipState ->
            if (dayOfWeekWithChipState.chipState == ChipState.SELECTED_WITH_ICON) indices.add(index)
        }
        return indices
    }

    private fun updateRegularDoseAmountText(value: String) {
        _uiState.update { it.copy(regularDoseAmount = value) }
    }

    private fun validateDosesNumberAndAmount() {
        val numberOfDosesValidationResult =
            validateNumberOfDosesUseCase(uiState.value.numberOfDosesPerDay)
        val amountValidationResult = validateRegularAmountText(uiState.value.regularDoseAmount)

        val resultList = listOf(numberOfDosesValidationResult, amountValidationResult)
        resultList.any { it.errorMessage != null }.also {
            _uiState.update {
                it.copy(
                    numberOfDosesPerDayErrorMessage = numberOfDosesValidationResult.errorMessage,
                    regularDoseAmountErrorMessage = amountValidationResult.errorMessage
                )
            }
        }
    }

    private fun validateRegularAmountText(amountText: String): ValidationResult {
        return if (uiState.value.isRegularDose)
            validateMedicineAmountUseCase(amountText)
        else ValidationResult(successful = true)
    }

    private fun clearSelectionWithIconForAllDaysExcept(index: Int) {
        if (index < 0 || index > uiState.value.daysOfWeekWithState.lastIndex) return
        _uiState.update {
            it.copy(
                daysOfWeekWithState = it.daysOfWeekWithState.mapIndexed { i, dayOfWeekWithChipState ->
                    val currentState = dayOfWeekWithChipState.chipState
                    if (i == index && currentState == ChipState.SELECTED) {
                        dayOfWeekWithChipState.copy(chipState = ChipState.SELECTED_WITH_ICON)
                    } else if (i != index && currentState == ChipState.SELECTED_WITH_ICON) {
                        dayOfWeekWithChipState.copy(chipState = ChipState.SELECTED)
                    } else {
                        dayOfWeekWithChipState
                    }
                }.toMutableList(),
            )
        }
        updateCurrentSelectedDayForTiming(getFirstSelectedWithIconDayIndex())
    }

    private fun onConfirmButtonClick() {
        if (uiState.value.currentSection == ProgramSection.DOSES_AND_AMOUNT) {
            val selectedWithIconIndices = getAllSelectedWithIconDaysIndices()
            updateNumberOfDosesPerDayTextAtIndices(selectedWithIconIndices)

            checkNumberOfDosesPerDayForAllSelectedDays()
            validateDosesNumberAndAmount()
        }
        if (uiState.value.currentSection == ProgramSection.TIMING) {
            checkAmountForAllTimesInAllDays()
        }
        if (uiState.value.numberOfDosesPerDayErrorMessage != null
            || uiState.value.regularDoseAmountErrorMessage != null
            || uiState.value.nonRegularDoseAmountErrorMessage != null
        ) return
        when (uiState.value.currentSection) {
            ProgramSection.DOSES_AND_AMOUNT -> {
                disableUnSelectedDays()
                if (uiState.value.isRegularDose) setAllSelectedDaysToSelectedWithIcon()
                else clearSelectionWithIconForAllDaysExcept(
                    getFirstSelectedOrSelectedWithIconDayIndex()
                )
                setCurrentSectionTo(ProgramSection.TIMING)
                createTimingListForSelectedDays()
                updateSetTimingSectionEnableState()
            }

            ProgramSection.TIMING -> {
                setAllSelectedDaysToSelectedWithIcon()
                setCurrentSectionTo(ProgramSection.MEDICATION_DURATION)
            }

            ProgramSection.MEDICATION_DURATION -> {}
        }
    }

    private fun setAllSelectedDaysToSelectedWithIcon() {
        uiState.value.daysOfWeekWithState.forEachIndexed { index, dayOfWeekWithState ->
            if (dayOfWeekWithState.chipState == ChipState.SELECTED || dayOfWeekWithState.chipState == ChipState.SELECTED_WITH_ICON)
                changeDayState(index, ChipState.SELECTED_WITH_ICON)
        }
    }

    private fun disableUnSelectedDays() {
        uiState.value.daysOfWeekWithState.forEachIndexed { index, dayOfWeekWithState ->
            if (dayOfWeekWithState.chipState == ChipState.UNSELECTED)
                changeDayState(index, ChipState.DISABLED)
        }
    }

    private fun checkNumberOfDosesPerDayForAllSelectedDays() {
        uiState.value.daysOfWeekWithState.forEachIndexed { index, dayOfWeekWithChipState ->
            val isSelectedOrSelectedWithIcon =
                dayOfWeekWithChipState.chipState == ChipState.SELECTED
                        || dayOfWeekWithChipState.chipState == ChipState.SELECTED_WITH_ICON
            if (isSelectedOrSelectedWithIcon) {
                val numberOfDosesValidationResult =
                    validateNumberOfDosesUseCase(dayOfWeekWithChipState.numberOfDosesPerDay)
                val amountValidationResult =
                    validateRegularAmountText(uiState.value.regularDoseAmount)

                if (numberOfDosesValidationResult.errorMessage == null && amountValidationResult.errorMessage == null) {
                    changeDayChipErrorState(index = index, isError = false)
                } else {
                    changeDayChipErrorState(index = index, isError = true)
                    if (!uiState.value.isRegularDose) clearSelectionWithIconForAllDaysExcept(index)
                    _uiState.update {
                        it.copy(
                            numberOfDosesPerDayErrorMessage = numberOfDosesValidationResult.errorMessage,
                            regularDoseAmountErrorMessage = numberOfDosesValidationResult.errorMessage,
                            numberOfDosesPerDay = it.daysOfWeekWithState[index].numberOfDosesPerDay
                        )
                    }
                    if (!uiState.value.isRegularDose) return
                }
            }
        }
    }

    private fun setCurrentSectionTo(section: ProgramSection) {
        _uiState.update { it.copy(currentSection = section) }
    }


    private fun updateIsSameTimingForAllDay(checked: Boolean) {
        _uiState.update { it.copy(isUnifiedTimingForAllDays = checked) }
        if (uiState.value.isUnifiedTimingForAllDays) {
            setSelectedWithIconStateForSelectedDays()
        } else {
            clearSelectionWithIconForAllDaysExcept(getFirstSelectedOrSelectedWithIconDayIndex())
            if (!uiState.value.isRegularDose) clearDoseAmountInAllDaysExceptFirstSelectedWithIcon()
        }
    }

    private fun clearDoseAmountInAllDaysExceptFirstSelectedWithIcon() {
        uiState.value.daysOfWeekWithState.forEachIndexed { index, dayOfWeekWithState ->
            if (dayOfWeekWithState.chipState == ChipState.UNSELECTED) {
            }
            //changeAmount(index, )
        }
    }

    private fun changeAmount(dayIndex: Int, timingIndex: Int, amount: String) {
        _uiState.update {
            it.copy(
                daysOfWeekWithState = it.daysOfWeekWithState.mapIndexed { currentDayIndex, dayOfWeekWithChipState ->
                    if (dayIndex == currentDayIndex) {
                        changeAmountAtTimingIndex(dayOfWeekWithChipState, timingIndex, amount)
                    } else {
                        dayOfWeekWithChipState
                    }
                }.toMutableList()
            )
        }
    }

    private fun changeAmountAtTimingIndex(
        dayOfWeekWithChipState: DayOfWeekWithChipState,
        timingIndex: Int,
        amount: String
    ): DayOfWeekWithChipState {
        return dayOfWeekWithChipState.copy(
            timings = dayOfWeekWithChipState.timings.mapIndexed { index, timing ->
                if (index == timingIndex) timing.copy(doseAmount = amount)
                else timing
            }.toMutableList()
        )
    }

    private fun createTimingListForSelectedDays() {
        _uiState.update {
            it.copy(
                daysOfWeekWithState = it.daysOfWeekWithState.map { dayOfWeekWithChipState ->
                    val isSelectedOrSelectedWithIcon =
                        dayOfWeekWithChipState.chipState == ChipState.SELECTED || dayOfWeekWithChipState.chipState == ChipState.SELECTED_WITH_ICON
                    if (isSelectedOrSelectedWithIcon)
                        dayOfWeekWithChipState.copy(
                            timings = createListOfTimingsWithTheSize(dayOfWeekWithChipState.numberOfDosesPerDay.toInt())
                        )
                    else dayOfWeekWithChipState
                }.toMutableList(),
                currentSelectedDayForTimingIndex = getFirstSelectedWithIconDayIndex()
            )
        }
    }

    private fun createListOfTimingsWithTheSize(size: Int): MutableList<Timing> {
        val timings = mutableListOf<Timing>()
        for (i in 0..<size)
            timings.add(
                Timing(
                    time = LocalTime.now(),
                    doseAmount = uiState.value.regularDoseAmount
                )
            )
        return timings
    }

    private fun updateNonRegularDoseAmountText(value: String) {
        _uiState.update {
            it.copy(
                nonRegularDoseAmount = value,
            )
        }
        val selectedWithIconDays = getAllSelectedWithIconDaysIndices()
        selectedWithIconDays.forEach { dayIndex ->
            val timingIndex = uiState.value.currentDoseOrder
            changeAmount(
                dayIndex = dayIndex,
                timingIndex = timingIndex,
                amount = uiState.value.nonRegularDoseAmount
            )
        }
    }

    private fun updateClickedDaySelectionStateInTimingSection(index: Int) {
        val dayOfWeekWithChipState = uiState.value.daysOfWeekWithState[index]
        if (uiState.value.isUnifiedTimingForAllDays) return
        if (dayOfWeekWithChipState.chipState == ChipState.SELECTED) {
            clearSelectionWithIconForAllDaysExcept(index)
            updateNonRegularDoseAmountText(getNonRegularDoseAmount())
            updateCurrentDoseOrder(order = 0)
            val selectedDayForTiming = getFirstSelectedWithIconDayIndex()
            if (selectedDayForTiming == -1) return
            updateCurrentSelectedDayForTiming(currentSelectedDayForTimingIndex = selectedDayForTiming)
        } else if (dayOfWeekWithChipState.chipState == ChipState.SELECTED_WITH_ICON) {
            changeDayState(index, chipState = ChipState.SELECTED)
            updateNonRegularDoseAmountText("")
        }
        updateSetTimingSectionEnableState()
    }

    private fun updateCurrentSelectedDayForTiming(currentSelectedDayForTimingIndex: Int) {
        _uiState.update {
            it.copy(
                currentSelectedDayForTimingIndex = currentSelectedDayForTimingIndex,
            )
        }
    }

    private fun updateCurrentDoseOrder(order: Int) {
        _uiState.update {
            it.copy(
                currentDoseOrder = order,
            )
        }
    }

    private fun getNonRegularDoseAmount(): String {
        val firstSelectedWithIcon = getFirstSelectedWithIconDayIndex()
        if (firstSelectedWithIcon == -1) return ""
        return uiState.value.daysOfWeekWithState[firstSelectedWithIcon].timings[uiState.value.currentDoseOrder].doseAmount
    }

    private fun updateSetTimingSectionEnableState() {
        val result =
            uiState.value.daysOfWeekWithState.firstOrNull { it.chipState == ChipState.SELECTED_WITH_ICON }
        if (result == null) updateIsTimingEnabled(false)
        else updateIsTimingEnabled(true)
    }

    private fun updateIsTimingEnabled(enable: Boolean) {
        _uiState.update { it.copy(isTimingSectionEnabled = enable) }
    }

    private fun moveCurrentSelectedDayAndDoseOrderBackward() {
        if (isMovingBackwardNotValid()) return
        if (getAllSelectedWithIconDaysIndices().isEmpty()) return

        if (uiState.value.currentDoseOrder == 0) {
            val previousSelectedDayIndex = getPreviousSelectedDay()
            if (previousSelectedDayIndex == -1) return
            clearSelectionWithIconForAllDaysExcept(previousSelectedDayIndex)
            _uiState.update {
                it.copy(
                    currentSelectedDayForTimingIndex = previousSelectedDayIndex,
                    currentDoseOrder = it.daysOfWeekWithState[previousSelectedDayIndex].timings.lastIndex,
                )
            }
            return
        }
        _uiState.update {
            it.copy(
                currentDoseOrder = it.currentDoseOrder - 1,
            )
        }
        updateNonRegularDoseAmountText(getNonRegularDoseAmount())
    }

    private fun isMovingBackwardNotValid(): Boolean {
        return uiState.value.currentSelectedDayForTimingIndex == getFirstSelectedOrSelectedWithIconDayIndex() && uiState.value.currentDoseOrder == 0
    }

    private fun getPreviousSelectedDay(): Int {
        val currentIndex =
            uiState.value.daysOfWeekWithState.indexOfFirst { it.chipState == ChipState.SELECTED_WITH_ICON }
        if (currentIndex == -1 || currentIndex == 0) {
            return -1
        }

        for (i in currentIndex - 1 downTo 0) {
            if (uiState.value.daysOfWeekWithState[i].chipState == ChipState.SELECTED) {
                return i
            }
        }

        return -1
    }

    private fun moveCurrentSelectedDayAndDoseOrderForward() {
        if (isMovingForwardNotValid()) return
        if (getAllSelectedWithIconDaysIndices().isEmpty()) return
        val currentDayTimingLastIndex =
            uiState.value.daysOfWeekWithState[getFirstSelectedWithIconDayIndex()].timings.lastIndex
        if (uiState.value.currentDoseOrder == currentDayTimingLastIndex) {
            val nextSelectedDayIndex = getNextSelectedDay()
            if (nextSelectedDayIndex == -1) return
            clearSelectionWithIconForAllDaysExcept(nextSelectedDayIndex)
            _uiState.update {
                it.copy(
                    currentSelectedDayForTimingIndex = nextSelectedDayIndex,
                    currentDoseOrder = 0,
                )
            }
            return
        }
        _uiState.update {
            it.copy(
                currentDoseOrder = it.currentDoseOrder + 1,
            )
        }
        updateNonRegularDoseAmountText(getNonRegularDoseAmount())
    }

    private fun isMovingForwardNotValid(): Boolean {
        val selectedOrSelectedWithIconDays = uiState.value.daysOfWeekWithState.filter {
            it.chipState == ChipState.SELECTED ||
                    it.chipState == ChipState.SELECTED_WITH_ICON
        }
        val lastDayIndex = selectedOrSelectedWithIconDays.lastIndex
        return uiState.value.currentSelectedDayForTimingIndex == lastDayIndex
                && uiState.value.currentDoseOrder == selectedOrSelectedWithIconDays[lastDayIndex].timings.lastIndex
    }

    private fun getNextSelectedDay(): Int {
        val currentIndex =
            uiState.value.daysOfWeekWithState.indexOfFirst { it.chipState == ChipState.SELECTED_WITH_ICON }
        val lastIndex = uiState.value.daysOfWeekWithState.lastIndex
        if (currentIndex == -1 || currentIndex == lastIndex) {
            return -1
        }

        for (i in currentIndex..lastIndex) {
            if (uiState.value.daysOfWeekWithState[i].chipState == ChipState.SELECTED) {
                return i
            }
        }
        return -1
    }

    private fun checkAmountForAllTimesInAllDays() {
        uiState.value.daysOfWeekWithState.forEachIndexed { dayIndex, dayOfWeekWithChipState ->
            val isSelectedOrSelectedWithIcon =
                dayOfWeekWithChipState.chipState == ChipState.SELECTED || dayOfWeekWithChipState.chipState == ChipState.SELECTED_WITH_ICON
            if (isSelectedOrSelectedWithIcon) {
                dayOfWeekWithChipState.timings.forEachIndexed { timingIndex, timing ->
                    val amountValidationResult = validateNonRegularAmountText(timing.doseAmount)

                    if (amountValidationResult.errorMessage == null) {
                        changeDayChipErrorState(index = dayIndex, isError = false)
                        _uiState.update {
                            it.copy(
                                nonRegularDoseAmountErrorMessage = null,
                            )
                        }
                    } else {
                        changeDayChipErrorState(index = dayIndex, isError = true)
                        updateCurrentDoseOrder(timingIndex)

                        clearSelectionWithIconForAllDaysExcept(
                            dayIndex
                        )

                        _uiState.update {
                            it.copy(
                                nonRegularDoseAmountErrorMessage = amountValidationResult.errorMessage,
                                nonRegularDoseAmount = it.daysOfWeekWithState[dayIndex].timings[timingIndex].doseAmount,
                            )
                        }
                        if (!uiState.value.isUnifiedTimingForAllDays) return
                    }

                }
            }
        }
    }

    private fun validateNonRegularAmountText(doseAmount: String): ValidationResult {
        return validateMedicineAmountUseCase(doseAmount)
    }


    private fun updateIsRepeatForever() {
        _uiState.update { it.copy(isRepeatForever = !it.isRepeatForever) }
    }

    private fun updateDoseDurationText(text: String) {
        _uiState.update { it.copy(medicationDuration = text) }
    }

    private fun updateDurationUnitsMenuExpandedState(expanded: Boolean) {
        _uiState.update { it.copy(isDurationUnitMenuExpanded = expanded) }
    }

    private fun updateSelectedDurationUnitIndex(index: Int) {
        _uiState.update { it.copy(selectedDurationUnitIndex = index) }
    }
}