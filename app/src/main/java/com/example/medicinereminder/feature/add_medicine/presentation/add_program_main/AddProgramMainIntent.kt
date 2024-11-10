package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main

sealed interface AddProgramMainIntent {
    data class UpdateSelectedTabIndex(val index:Int):AddProgramMainIntent
}