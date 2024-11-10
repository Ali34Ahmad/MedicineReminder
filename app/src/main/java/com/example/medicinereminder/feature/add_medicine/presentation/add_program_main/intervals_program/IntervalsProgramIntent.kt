package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.intervals_program

sealed interface IntervalsProgramIntent {
    data class UpdateRepeatEveryText(val text:String):IntervalsProgramIntent
    data class UpdateSelectedRepetitionUnitIndex(val index:Int):IntervalsProgramIntent
    data class UpdateRepetitionUnitMenuExpandedState(val expanded:Boolean):IntervalsProgramIntent

    data class UpdateIsRegularDoseCheckedState(val checked: Boolean) : IntervalsProgramIntent
    data class UpdateAmountText(val text: String):IntervalsProgramIntent
    data class UpdateNumberOfDosesPerDayText(val text: String):IntervalsProgramIntent

    data class UpdateNonRegularAmountTextValueChange(val text: String):IntervalsProgramIntent
    
    data class UpdateIsRepeatForever(val checked: Boolean) : IntervalsProgramIntent
    data class UpdateMedicationDurationText(val text: String) : IntervalsProgramIntent
    data class UpdateMedicationDurationUnitMenuExpandedState(val expanded: Boolean) : IntervalsProgramIntent
    data class UpdateSelectedMedicationDurationUnitIndex(val index: Int) : IntervalsProgramIntent


    data object TimingBackButtonClick : IntervalsProgramIntent
    data object TimingNextButtonClick : IntervalsProgramIntent

    data object ConfirmButtonClick:IntervalsProgramIntent
    data object CancelButtonClick:IntervalsProgramIntent

}