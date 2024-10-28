package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.intervals_program

import com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.day_specific_program.ProgramSection
import java.time.LocalTime

data class IntervalsProgramUiState(
    val repeatEveryText:String="",
    val repeatEveryTextErrorMessage:Int?=null,
    val selectedRepetitionDurationUnitIndex: Int = 0,
    val isRepetitionDurationUnitMenuExpanded: Boolean=false,

    val numberOfDosesPerDayText:String="",
    val numberOfDosesPerDayErrorMessage:Int?=null,

    val isRegularDose:Boolean=true,
    val regularDoseAmountText:String="",
    val regularDoseAmountErrorMessage:Int?=null,

    val timesWithAmountInIntervalProgram:List<Pair<LocalTime,String>> = emptyList(),
    val currentDoseNumberSelectedForTiming:Int=0,

    val nonRegularDoseAmount:String="",
    val nonRegularDoseAmountErrorMessage:Int?=null,

    val isRepeatForever: Boolean = false,
    val medicationDuration: String = "",
    val medicationDurationErrorMessage: Int? = null,
    val selectedMedicationDurationUnitIndex: Int = 0,
    val isMedicationDurationUnitMenuExpanded: Boolean=false,
    val durationUnitErrorMessage: Int? = null,

    val currentSection: ProgramSection = ProgramSection.DOSES_AND_AMOUNT,
)