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
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.model.Address
import com.example.medicinereminder.feature.add_doctor.presentation.existing_doctors_bottom_sheet.DoctorsBottomSheetViewModel
import com.example.medicinereminder.feature.add_doctor.presentation.main_screen.AddDoctorScreen
import com.example.medicinereminder.feature.add_doctor.presentation.main_screen.AddDoctorViewModel
import com.example.medicinereminder.feature.doctor_details.presentation.DoctorDetailsScreen
import com.example.medicinereminder.feature.doctor_details.presentation.DoctorDetailsUIState
import com.example.medicinereminder.feature.doctor_details.presentation.DoctorDetailsViewModel
import com.example.medicinereminder.presentation.ui.helper.appointmentTableItems
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
                            doctor = Doctor(
                                name = "Dr. Jaoher Zenah",
                                phoneNumber = "1234567890",
                                specialty = "Dentist",
                                imageFileName = "",
                                address = Address(
                                    stateOrGovernorate = "Syria",
                                    city = "Lattakia",
                                    street = "Al Zera'ah _ Al Awokaf"
                                )
                            ),
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
