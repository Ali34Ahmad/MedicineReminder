package com.example.medicinereminder.presentation.medicine_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.top_app_bar.StandardTopAppBarComponent
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun MedicineDetailsScreen(
    uiState: MedicineDetailsUiState,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Box(modifier = Modifier.fillMaxWidth()){
                Image(
                    painter = painterResource(id = R.drawable.medicine_image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                StandardTopAppBarComponent(
                    showTrailingIcon = true,
                    title = null,
                    onNavigateUpClick = {},
                    onTrailingIconClick = {},
                    showNavigateUp = true
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun MedicineDetailsHeaderPreview() {
    MedicineReminderTheme {
        Surface {
            MedicineDetailsScreen(
                MedicineDetailsUiState()
            )
        }
    }
}
