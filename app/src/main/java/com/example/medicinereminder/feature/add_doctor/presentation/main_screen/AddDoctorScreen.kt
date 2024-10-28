package com.example.medicinereminder.feature.add_doctor.presentation.main_screen

import RichButtonWithIconAndText
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.bottom_sheet.ExistingDoctorsBottomSheet
import com.example.medicinereminder.common.components.buttons.FooterButtons
import com.example.medicinereminder.common.components.composables.ScreenIndicator
import com.example.medicinereminder.common.components.text_field.OutlinedTextFieldComponent
import com.example.medicinereminder.common.components.texts.HeadingText
import com.example.medicinereminder.common.components.top_app_bar.StandardTopAppBarComponent
import com.example.medicinereminder.data.local.doctor1
import com.example.medicinereminder.data.local.doctor2
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.feature.add_doctor.presentation.existing_doctors_bottom_sheet.DoctorsBottomSheetAction
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDoctorScreen(
    modifier: Modifier = Modifier,
    uiState: AddDoctorUIState,
    currentPageNumber: Int,
    numberOfPages: Int,
    @StringRes title: Int,
    onAddDoctorAction: (AddDoctorAction) -> Unit,
    existingDoctors: List<Doctor>,
    doctorsQuery: String,
    onDoctorsBottomSheetAction: (DoctorsBottomSheetAction) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            StandardTopAppBarComponent(
                showTrailingIcon = false,
                showNavigateUp = true,
                title = title,
                onNavigateUpClick = {
                    onAddDoctorAction(AddDoctorAction.NavigateUp)
                },
                onTrailingIconClick = {})
        },
        bottomBar = {
            FooterButtons(
                onLeftButtonClick = { onAddDoctorAction(AddDoctorAction.Cancel) },
                onRightButtonClick = { onAddDoctorAction(AddDoctorAction.Confirm) },
                leftButtonText = R.string.cancel,
                rightButtonText = R.string.confirm,
                hasErrorColor = false
            )
        }
    ) { innerPadding ->

        Surface(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged {
                            if (it.isFocused) {
                                onAddDoctorAction(AddDoctorAction.ShowExistingDoctorsMenu)
                            }
                        },
                    value = uiState.doctorName,
                    onValueChange = {
                        onAddDoctorAction(AddDoctorAction.UpdateDoctorName(it))
                    },
                    label = R.string.doctor_name
                )
                OutlinedTextFieldComponent(
                    value = uiState.specialty,
                    onValueChange = {
                        onAddDoctorAction(AddDoctorAction.UpdateSpecialty(it))
                    },
                    label = R.string.specialty,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = MaterialTheme.spacing.medium24
                        )
                )
                AnimatedContent(
                    targetState = uiState.isContactInfoShown, label = "",
                    transitionSpec = {
                        fadeIn() + scaleIn() togetherWith fadeOut() + scaleOut()
                    }
                ) { targetState ->
                    when (targetState) {
                        false -> {
                            //Add contact info
                            RichButtonWithIconAndText(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = MaterialTheme.spacing.medium16),
                                icon = Icons.Outlined.Phone,
                                title = R.string.add_contact_info,
                                desc = R.string.keep_doctor_handy,
                                onClick = {
                                    onAddDoctorAction(AddDoctorAction.ShowContactInfo)
                                }
                            )
                        }

                        true -> {
                            Column {
                                // Contact info section
                                HeadingText(
                                    title = R.string.contact_info,
                                    isOptional = true,
                                    modifier = Modifier
                                        .align(Alignment.Start)
                                )
                                OutlinedTextFieldComponent(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = MaterialTheme.spacing.medium16,
                                            bottom = MaterialTheme.spacing.medium24
                                        ),
                                    value = uiState.phoneNumber ?: "",
                                    onValueChange = {
                                        onAddDoctorAction(AddDoctorAction.UpdatePhoneNumber(it))
                                    },
                                    label = R.string.phone_number
                                )
                            }

                        }
                    }
                }

                AnimatedContent(
                    targetState = uiState.isAddressInfoShown,
                    label = "",
                    transitionSpec = {
                        fadeIn() + scaleIn() togetherWith fadeOut() + scaleOut()
                    }) { targetState ->
                    when (targetState) {
                        false -> {
                            //Add contact info
                            RichButtonWithIconAndText(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = MaterialTheme.spacing.medium16),
                                icon = Icons.Outlined.Location,
                                title = R.string.add_address,
                                desc = R.string.remember_clinic_address,
                                onClick = {
                                    onAddDoctorAction(AddDoctorAction.ShowAddressInfo)
                                }
                            )
                        }

                        true -> {
                            Column {
                                // Address info section
                                HeadingText(
                                    title = R.string.address, isOptional = true,
                                    modifier = Modifier
                                        .align(Alignment.Start)
                                        .padding(bottom = MaterialTheme.spacing.medium16)
                                )
                                OutlinedTextFieldComponent(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = uiState.address.stateOrGovernorate,
                                    onValueChange = {
                                        onAddDoctorAction(AddDoctorAction.UpdateStateOrGovernment(it))
                                    },
                                    label = R.string.state_government,
                                )
                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium24))
                                OutlinedTextFieldComponent(
                                    value = uiState.address.city,
                                    onValueChange = {
                                        onAddDoctorAction(AddDoctorAction.UpdateCity(it))
                                    },
                                    label = R.string.city,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium24))
                                OutlinedTextFieldComponent(
                                    value = uiState.address.street ?: "",
                                    onValueChange = {
                                        onAddDoctorAction(
                                            AddDoctorAction.UpdateStreet(
                                                it
                                            )
                                        )
                                    },
                                    label = R.string.street,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium24))
                                OutlinedTextFieldComponent(
                                    value = uiState.note,
                                    onValueChange = {
                                        onAddDoctorAction(
                                            AddDoctorAction.UpdateNote(
                                                it
                                            )
                                        )
                                    },
                                    label = R.string.addition_notes,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium24))
                            }
                        }
                    }
                }
                RichButtonWithIconAndText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = MaterialTheme.spacing.medium16),
                    icon = Icons.Outlined.Search,
                    title = R.string.search_existing_doctors,
                    desc = R.string.facilitate_add_doctor_process,
                    onClick = {
                        onAddDoctorAction(AddDoctorAction.ShowBottomSheet)
                    }
                )
                if (uiState.isBottomSheetShown) {
                    ExistingDoctorsBottomSheet(
                        doctors = existingDoctors,
                        onDismissRequest = {
                            onAddDoctorAction(AddDoctorAction.HideBottomSheet)
                        },
                        sheetState = sheetState,
                        query = doctorsQuery,
                        onQueryChange = {
                            onDoctorsBottomSheetAction(
                                DoctorsBottomSheetAction.UpdateQuery(it)
                            )
                        },
                        onClearButtonClick = {
                            onDoctorsBottomSheetAction(
                                DoctorsBottomSheetAction.UpdateQuery("")
                            )
                        },
                        onDoctorItemClick = {
                            onDoctorsBottomSheetAction(
                                DoctorsBottomSheetAction.SelectDoctor(it)
                            )
                        },
                        onDetailsButtonClick = {
                            onDoctorsBottomSheetAction(
                                DoctorsBottomSheetAction.ClickDetailsButton(it)
                            )
                        }
                    )
                }

            }
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
            onAddDoctorAction = {},
            existingDoctors = listOf(
                doctor1,
                doctor2
            ),
            doctorsQuery = "ali",
            onDoctorsBottomSheetAction = {}
        )
    }
}