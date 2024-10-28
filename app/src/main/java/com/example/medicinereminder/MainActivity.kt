package com.example.medicinereminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.AddProgramMainScreen
import com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.AddProgramMainState
import com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.day_specific_program.DaySpecificProgramUiState
import com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.day_specific_program.SetMedicationDurationSection
import com.example.medicinereminder.presentation.add_medicine_program.AddMedicineProgramUiState
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MedicineReminderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        AddProgramMainScreen(
                            uiState = AddProgramMainState(),
                            onIntent ={}
                        )
                    }
                }
            }
        }
    }
}