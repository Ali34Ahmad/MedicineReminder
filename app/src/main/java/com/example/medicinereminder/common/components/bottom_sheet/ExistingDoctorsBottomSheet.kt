package com.example.medicinereminder.common.components.bottom_sheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.list_item.ExistingDoctorItem
import com.example.medicinereminder.common.components.text_field.SearchTextField
import com.example.medicinereminder.common.utility.extension.doesMatchSearchQuery
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingDoctorsBottomSheet(
    modifier: Modifier = Modifier,
    doctors: List<Doctor>,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    query: String,
    onQueryChange: (String) -> Unit,
    onClearButtonClick: () -> Unit,
    onDoctorItemClick: (Doctor) -> Unit,
    onDetailsButtonClick: (Doctor) -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        modifier = modifier.fillMaxHeight(),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
    ) {
        SearchTextField(
            query = query,
            onQueryChange = onQueryChange,
            onClearButtonClick = onClearButtonClick
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
        doctors.forEach { doctor ->
            ExistingDoctorItem(
                doctor = doctor,
                onClick = onDoctorItemClick,
                onButtonClick = onDetailsButtonClick,
                defaultImage = R.drawable.doctor_image
            )
            HorizontalDivider()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ExistingDoctorsBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var query by remember{
        mutableStateOf("")
    }
    val doctors = listOf(
                Doctor(
                    name = "Ali",
                    specialty = "Eyes",
                    imageFileName = "",
                ),
                Doctor(
                    name = "Mohamed",
                    specialty = "Eyes",
                    imageFileName = "",
                ),
                Doctor(
                    name = "Salem",
                    specialty = "Eyes",
                    imageFileName = "",
                ),
                Doctor(
                    name = "Elias",
                    specialty = "Eyes",
                    imageFileName = "",
                )
        ).filter {
            if (query.isNotBlank()){
                it.doesMatchSearchQuery(query)
            }else
                true
        }



    MedicineReminderTheme {
        ExistingDoctorsBottomSheet(
            doctors = doctors,
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                }
            },
            sheetState = sheetState,
            onClearButtonClick = {
                query = ""
            },
            onQueryChange = {
                query = it
            },
            query = query,
            onDoctorItemClick = {},
            onDetailsButtonClick = {}
        )
    }
}