package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.day_specific_program

import com.example.medicinereminder.presentation.ui.constants.ChipState
import java.time.DayOfWeek
import java.time.LocalTime

data class DaySpecificProgramUiState(
    val daysOfWeekWithState: MutableList<DayOfWeekWithChipState> = selectableDaysOfWeek()
        .toMutableList(),
    val isRegularDose: Boolean = true,
    val numberOfDosesPerDay: String = "",
    val numberOfDosesPerDayErrorMessage: Int? = null,
    val regularDoseAmount: String = "",
    val regularDoseAmountErrorMessage: Int? = null,

    val isTimingSectionEnabled: Boolean = false,
    val isUnifiedTimingForAllDays: Boolean = true,
    val nonRegularDoseAmount: String = "",
    val nonRegularDoseAmountErrorMessage: Int? = null,
    val currentSelectedDayForTimingIndex: Int? = null,
    val currentDoseOrder: Int = 0,
    val isNextButtonEnabled: Boolean=true,
    val isBackButtonEnabled: Boolean=true,

    val isRepeatForever: Boolean = false,
    val medicationDuration: String = "",
    val medicationDurationErrorMessage: Int? = null,
    val selectedDurationUnitIndex: Int = 0,
    val isDurationUnitMenuExpanded: Boolean=false,
    val durationUnitErrorMessage: Int? = null,

    val currentSection: ProgramSection = ProgramSection.DOSES_AND_AMOUNT,
)

data class DayOfWeekWithChipState(
    val dayOfWeek: DayOfWeek,
    val chipState: ChipState,
    val isError: Boolean,
    val numberOfDosesPerDay: String,
    val timings: MutableList<Timing>,
)

data class Timing(
    val time: LocalTime,
    val doseAmount: String
)
enum class ProgramSection{
    DOSES_AND_AMOUNT,TIMING,MEDICATION_DURATION,
}

private fun selectableDaysOfWeek(): List<DayOfWeekWithChipState> {
    return DayOfWeek.entries.map {
        DayOfWeekWithChipState(
            dayOfWeek = it,
            chipState = ChipState.UNSELECTED,
            timings = mutableListOf(),
            numberOfDosesPerDay = "",
            isError = false,
        )
    }
}
val timing=Timing(time = LocalTime.now(), doseAmount = "")