package com.example.medicinereminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.core.components.composables.MedicalForm
import com.example.medicinereminder.data.local.AppDatabase
import com.example.medicinereminder.ui.theme.MedicineReminderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedicineReminderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppDatabase.getInstance(this)
                    var isExpanded by remember{
                        mutableStateOf(false)
                    }
                    MedicalForm(
                        modifier = Modifier.padding(innerPadding),
                        title = "Medical Form",
                        items = listOf(
                            "Capsule", "Tablet", "Syrup","Suspension",
                            "Injection","Capsule","Suppository"
                            ,"Capsule", "Tablet", "Syrup","Suspension",
                            "Injection","Capsule","Suppository"
                        ),
                        onChooseItem = {},
                        onExpandClick = {
                            isExpanded = !isExpanded
                        },
                        firstStateText = "Show all",
                        secondStateText ="Show less",
                        firstStateIcon = R.drawable.baseline_expand_more_24,
                        secondStateIcon = R.drawable.baseline_expand_less_24,
                        selectedItemIndex = 3,
                        isExpanded = isExpanded
                    )
                        

                }
            }
        }
    }
}