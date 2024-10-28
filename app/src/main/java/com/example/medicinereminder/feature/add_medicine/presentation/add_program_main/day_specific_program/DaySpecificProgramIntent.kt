package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.day_specific_program

sealed interface DaySpecificProgramIntent {
    data class UpdateSelectedDay(val index:Int):DaySpecificProgramIntent
    data class LongClickDayChip(val index:Int):DaySpecificProgramIntent
    data class UpdateIsRegularDose(val checked:Boolean):DaySpecificProgramIntent
    data class UpdateNumberOfDosesPerDayText(val text:String):DaySpecificProgramIntent
    data class UpdateRegularDoseAmountText(val text:String):DaySpecificProgramIntent

    data class UpdateIsSameTimingForAllDays(val checked: Boolean) : DaySpecificProgramIntent
    data class UpdateNonRegularDoseAmount(val text: String) : DaySpecificProgramIntent
    data object TimingsBackButtonClick : DaySpecificProgramIntent
    data object TimingsNextButtonClick : DaySpecificProgramIntent

    data object UpdateIsRepeatForever : DaySpecificProgramIntent
    data class UpdateDoseDurationText(val text:String) : DaySpecificProgramIntent
    data class UpdateSelectedDayForTiming(val index: Int) : DaySpecificProgramIntent
    data class UpdateSelectedDurationUnitIndex(val index: Int) : DaySpecificProgramIntent
    data class UpdateDurationUnitsMenuExpandedState(val expanded: Boolean) : DaySpecificProgramIntent

    data object UpdateDoseDurationUnit : DaySpecificProgramIntent

    data object ConfirmButtonClick : DaySpecificProgramIntent
    data object CancelButtonClick : DaySpecificProgramIntent
}