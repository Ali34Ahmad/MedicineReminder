package com.example.medicinereminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicinereminder.feature.add_medicine.add_doctor.AddDoctorScreen
import com.example.medicinereminder.feature.add_medicine.add_doctor.AddDoctorViewModel
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MedicineReminderTheme {
                val viewModel = hiltViewModel<AddDoctorViewModel>()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                            val uiState = viewModel.uiState.value
                            AddDoctorScreen(
                                currentPageNumber = 2,
                                numberOfPages = 3,
                                title = R.string.add_doctor,
                                uiState = uiState,
                                onAction = viewModel::onAction
                            )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    MaterialTheme {
        Surface {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_medicine_outlined),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
        }
    }
}