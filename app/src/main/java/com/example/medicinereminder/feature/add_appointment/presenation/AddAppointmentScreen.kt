@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.medicinereminder.feature.add_appointment.presenation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.buttons.ButtonRow
import com.example.medicinereminder.common.components.composables.PickDateComponent
import com.example.medicinereminder.common.components.dialog.CustomDatePickerDialog
import com.example.medicinereminder.common.components.top_app_bar.StandardTopAppBarComponent
import com.example.medicinereminder.data.local.doctor1
import com.example.medicinereminder.feature.add_appointment.component.card.DoctorCard
import com.example.medicinereminder.feature.add_appointment.component.dialog.ReplaceDoctorDialogBox
import com.example.medicinereminder.feature.add_appointment.component.time_picker.CustomTimePicker
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun AddAppointmentScreen(
    modifier: Modifier = Modifier,
    onAction: (AddAppointmentAction) -> Unit,
    uiState: AddAppointmentUIState
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            StandardTopAppBarComponent(
                showTrailingIcon = true,
                showNavigateUp = true,
                title = R.string.add_appointment,
                onNavigateUpClick = {
                    onAction(
                        AddAppointmentAction.NavigateBack
                    )
                },
                trailingIcon = R.drawable.ic_switch,
                onTrailingIconClick = {
                    onAction(
                        AddAppointmentAction.ShowDialogBox
                    )
                }
            )
        },
        bottomBar = {
            ButtonRow(
                primaryButtonText = R.string.confirm,
                isPrimaryButtonEnabled = uiState.isConfirmButtonEnabled,
                onPrimaryButtonClick = {
                    onAction(
                        AddAppointmentAction.Confirm
                    )
                },
                secondaryButtonText = R.string.cancel,
                onSecondaryButtonClick = {
                    onAction(
                        AddAppointmentAction.Cancel
                    )
                }
            )
        }
    ) {innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
                DoctorCard(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16),
                    doctor = uiState.doctor, isExpanded = uiState.isDetailsShown,
                    onExpand = {
                        onAction(AddAppointmentAction.ToggleDetailsVisibility)
                    }
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
                HorizontalDivider(
                    thickness = MaterialTheme.spacing.small8,
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
                PickDateComponent(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16),
                    startDate = LocalDate.now(),
                    endDate = LocalDate.now().plusMonths(1),
                    onDateSelected = {
                        onAction(
                            AddAppointmentAction.SelectDate(it)
                        )
                    },
                    onButtonClick = {
                        onAction(
                            AddAppointmentAction.ShowCalender
                        )
                    },
                    selectedDate = uiState.selectedDate
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
                HorizontalDivider(
                    thickness = MaterialTheme.spacing.small8,
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
                CustomTimePicker(
                    modifier = Modifier.fillMaxWidth().padding(
                        horizontal = MaterialTheme.spacing.medium16
                    ),
                    onPeriodSelected = {
                        onAction(
                            AddAppointmentAction.SelectPeriod(it)
                        )
                    },
                    onHourSelected = {
                        onAction(
                            AddAppointmentAction.SelectHour(it)
                        )
                    },
                    onMinutesSelected = {
                        onAction(
                            AddAppointmentAction.SelectMinute(it)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
            }
                CustomDatePickerDialog(
                    onConfirmClick = {
                        onAction(
                            AddAppointmentAction.SelectDate(it)
                        )
                    },
                    onDismissRequest = {
                        onAction(
                            AddAppointmentAction.HideCalender
                        )
                    },
                    showDialog = uiState.isCalenderShown,
                    confirmButtonText = R.string.confirm,
                    dismissButtonText = R.string.cancel
                )
            if(uiState.isDialogBoxShown){
                ReplaceDoctorDialogBox(
                    doctorName = uiState.doctor.name,
                    onDismissRequest = {
                        onAction(
                            AddAppointmentAction.HideDialogBox
                        )
                    },
                    onCancel = {
                        onAction(
                            AddAppointmentAction.HideDialogBox
                        )
                    },
                    onConfirm = {
                        onAction(
                            AddAppointmentAction.ReplaceDoctor
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddAppointmentScreenPreview() {
    MedicineReminderTheme {
        var uiState by remember {
            mutableStateOf(AddAppointmentUIState(doctor = doctor1))
        }
        AddAppointmentScreen(
            onAction = {
                when(it){
                    AddAppointmentAction.HideCalender -> {
                        uiState = uiState.copy(isCalenderShown = false)
                    }
                    AddAppointmentAction.ShowCalender ->  {
                        uiState = uiState.copy(isCalenderShown = true)
                    }
                    AddAppointmentAction.ToggleDetailsVisibility -> {
                        uiState = uiState.copy(isDetailsShown = uiState.isDetailsShown.not())
                    }
                    AddAppointmentAction.ShowDialogBox -> {
                        uiState = uiState.copy(isDialogBoxShown = true)
                    }
                    is AddAppointmentAction.SelectPeriod ->{
                        uiState = uiState.copy(selectedPeriod = it.period)
                    }
                    is AddAppointmentAction.SelectHour ->{
                        uiState = uiState.copy(selectedHour = it.hour)
                    }
                    is AddAppointmentAction.SelectMinute ->{
                        uiState = uiState.copy(selectedMinute = it.minute)
                    }
                    is AddAppointmentAction.SelectDate -> {
                        uiState = uiState.copy(selectedDate = it.date)
                        if(!uiState.isConfirmButtonEnabled){
                            uiState = uiState.copy(isConfirmButtonEnabled = true)
                        }
                    }
                    else -> {}
                }
            },
            uiState = uiState
        )
    }
}