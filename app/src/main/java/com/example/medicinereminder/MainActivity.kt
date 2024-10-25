package com.example.medicinereminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.medicinereminder.feature.doctor_details.presentation.DoctorDetailsScreen
import com.example.medicinereminder.feature.doctor_details.presentation.DoctorDetailsViewModel
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            val viewModel: DoctorDetailsViewModel = hiltViewModel()
            val uiState by viewModel.uiState
            val tableItems by viewModel.tableItems.collectAsStateWithLifecycle()

            MedicineReminderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        DoctorDetailsScreen(
                            tableItems = tableItems,
                            uiState = uiState,
                            onAction = viewModel::onAction
                        )
                    }
                }
            }
        }
    }
}
