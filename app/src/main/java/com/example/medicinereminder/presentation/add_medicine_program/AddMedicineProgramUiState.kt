package com.example.medicinereminder.presentation.add_medicine_program

import java.time.DayOfWeek
import java.time.LocalTime

data class AddMedicineProgramUiState(
    val selectedChipIndex:Int=0,
    val isRepeatForever:Boolean=true,
    val medicationDurationText:String="",
    val selectedMedicationDurationUnitIndex:Int=0,
    val numberOfDosesPerDay:Int=0,

    //Specific Days Program
    val selectedDaysOfWeekWithCurrentSelectedDose:List<Pair<DayOfWeek,Int>> = List(DayOfWeek.entries.size){
        DayOfWeek.entries[it] to 1
    },
    val selectedDayOfWeekToSpecifyTimingsIndex:Int = 0,
    val isRegularDose:Boolean=true,
    val isSameTimingForAllDays:Boolean=true,
    val timesWithAmountInDaysProgram:List<Pair<LocalTime,Int>> = List(selectedDaysOfWeekWithCurrentSelectedDose.size+numberOfDosesPerDay){
        LocalTime.now() to 1
    },

    //Interval Program
    val repeatEveryText:String,
    val selectedRepeatEveryDurationUnitIndex:Int=0,
    val timesWithAmountInIntervalProgram:List<Pair<LocalTime,Int>> = List(numberOfDosesPerDay){
        LocalTime.now() to 1
    },
    val currentDoseNumberSelectedForTiming:Int=0,

    //Cycle Program
    val doseDurationText:String,
    val selectedDoseDurationUnitIndex:Int=0,

    val breakDurationText:String,
    val selectedBreakDurationUnitIndex:Int=0,
)
