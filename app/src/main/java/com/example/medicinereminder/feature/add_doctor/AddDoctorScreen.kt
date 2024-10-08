package com.example.medicinereminder.feature.add_doctor

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.buttons.FooterButtons
import com.example.medicinereminder.common.components.composables.ScreenIndicator
import com.example.medicinereminder.common.components.text_field.OutlinedTextFieldComponent
import com.example.medicinereminder.common.components.texts.HeadingText
import com.example.medicinereminder.common.components.top_app_bar.StandardTopAppBarComponent
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun AddDoctorScreen(
    modifier: Modifier = Modifier,
    uiState: AddDoctorUIState,
    currentPageNumber: Int,
    numberOfPages: Int,
    @StringRes title: Int,
    onAction: (AddDoctorAction) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            StandardTopAppBarComponent(
                showTrailingIcon = false,
                showNavigateUp = true,
                title = title,
                onNavigateUpClick = {
                    onAction(AddDoctorAction.NavigateUp)
                },
                onTrailingIconClick = {})
        },
        bottomBar = {
            FooterButtons(
                onLeftButtonClick = {onAction(AddDoctorAction.Cancel)},
                onRightButtonClick =  {onAction(AddDoctorAction.Confirm)},
                leftButtonText = R.string.cancel,
                rightButtonText = R.string.confirm,
                hasErrorColor = false
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(
                    horizontal = MaterialTheme.spacing.medium16,
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ScreenIndicator(
                currentPage = currentPageNumber,
                numberOfPages = numberOfPages,
                modifier = Modifier.padding(MaterialTheme.spacing.medium24)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
            OutlinedTextFieldComponent(
                value = uiState.doctorName,
                onValueChange = {
                    onAction(AddDoctorAction.UpdateDoctorName(it))
                },
                label = R.string.doctor_name
            )
            OutlinedTextFieldComponent(
                value = uiState.specialty,
                onValueChange = {
                    onAction(AddDoctorAction.UpdateSpecialty(it))
                },
                label = R.string.specialty,
                modifier = Modifier.padding(
                    vertical = MaterialTheme.spacing.medium24
                )
            )
            HeadingText(
                title = R.string.contact_info,
                isOptional = true,
                modifier = Modifier
                    .align(Alignment.Start)
            )
            OutlinedTextFieldComponent(
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.medium16,
                    bottom = MaterialTheme.spacing.medium24
                ),
                value = uiState.phoneNumber,
                onValueChange = {
                    onAction(AddDoctorAction.UpdatePhoneNumber(it))
                } ,
                label = R.string.phone_number
            )
            HeadingText(
                title = R.string.address, isOptional = true,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = MaterialTheme.spacing.medium16)
            )
            OutlinedTextFieldComponent(
                value = uiState.address.stateOrGovernorate,
                onValueChange ={
                    onAction(AddDoctorAction.UpdateStateOrGovernment(it))
                },
                label = R.string.state_government,
            )
            OutlinedTextFieldComponent(
                value = uiState.address.city,
                onValueChange = {
                    onAction(AddDoctorAction.UpdateCity(it))
                } ,
                label = R.string.city,
                modifier = Modifier.padding(
                    vertical = MaterialTheme.spacing.medium24
                )
            )
            OutlinedTextFieldComponent(
                value = uiState.address.street?:"",
                onValueChange = { onAction(AddDoctorAction.UpdateStreet(it)) } ,
                label = R.string.street,
            )
            OutlinedTextFieldComponent(
                value = uiState.note,
                onValueChange = { onAction(AddDoctorAction.UpdateNote(it))} ,
                label = R.string.addition_notes,
                modifier = Modifier.padding(
                    vertical = MaterialTheme.spacing.medium24
                )
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddDoctorPreview() {
    MedicineReminderTheme {
        AddDoctorScreen(
            currentPageNumber = 2,
            numberOfPages = 3,
            title = R.string.add_doctor,
            uiState = AddDoctorUIState(),
            onAction = {}
        )
    }
}