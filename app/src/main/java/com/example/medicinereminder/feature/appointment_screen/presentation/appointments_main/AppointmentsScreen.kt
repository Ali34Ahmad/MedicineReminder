package com.example.medicinereminder.feature.appointment_screen.presentation.appointments_main

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.bottom_sheet.AppointmentBottomSheet
import com.example.medicinereminder.common.components.bottom_sheet.ExistingDoctorsBottomSheet
import com.example.medicinereminder.common.components.bottom_sheet.ExistingDoctorsBottomSheetPreview
import com.example.medicinereminder.common.components.lazy_column.AppointmentsLazyColumn
import com.example.medicinereminder.common.components.tab.ScrollableTab
import com.example.medicinereminder.common.components.top_app_bar.StandardTopAppBarComponent
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.local.appointment1
import com.example.medicinereminder.data.local.appointment2
import com.example.medicinereminder.data.local.doctor1
import com.example.medicinereminder.data.local.doctor2
import com.example.medicinereminder.data.local.doctorsAppointments
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.data.model.DoctorWithAppointment
import com.example.medicinereminder.feature.appointment_screen.presentation.add_appointment_bottom_sheet.AddAppointmentBottomSheetAction
import com.example.medicinereminder.feature.appointment_screen.presentation.appointment_details_bootm_sheet.AppointmentDetailsBottomSheetAction
import com.example.medicinereminder.presentation.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentsScreen(
    modifier: Modifier = Modifier,
    onAppointmentAction: (AppointmentsAction) -> Unit,
    onAppointmentDetails:(AppointmentDetailsBottomSheetAction) -> Unit,
    onAddAppointmentsAction: (AddAppointmentBottomSheetAction) -> Unit,
    reminders: List<DoctorWithAppointment>,
    tabs: List<AppointmentsTab> = AppointmentsTab.entries,
    existingDoctors: List<Doctor>,
    query: String,
    uiState: AppointmentsUiState,
    @StringRes title: Int
) {
    val scope = rememberCoroutineScope()
    val upcoming = reminders.filter { it.appointment.reminderState == ReminderState.UPCOMING }
    val completed = reminders.filter { it.appointment.reminderState == ReminderState.TAKEN }
    val missed = reminders.filter { it.appointment.reminderState == ReminderState.MISSED }
    val stopped =reminders.filter { it.appointment.reminderState == ReminderState.STOPPED }
    val unspecified =reminders.filter { it.appointment.reminderState == ReminderState.UNSPECIFIED }
    val badges = listOf(
        upcoming.size,
        completed.size,
        missed.size,
        stopped.size,
        unspecified.size
    )
    val appointmentDetailsSheetState = rememberModalBottomSheetState()
    val existingDoctorsSheetState = rememberModalBottomSheetState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            StandardTopAppBarComponent(
                showTrailingIcon = false,
                showNavigateUp = false,
                title = title
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAppointmentAction(
                    AppointmentsAction.OpenBottomSheet(AppointmentBottomSheet.ADD_APPOINTMENT,null)
                )
            }) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = null)
            }
        }
    ){innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ){
            Column(
                modifier = Modifier.fillMaxSize()
            ){
                ScrollableTab(
                    itemsStringRes = tabs.map { it.title },
                    showBadges = true,
                    selectedItemIndex = uiState.currentTab.ordinal,
                    onTabClick = {index ->
                        onAppointmentAction(AppointmentsAction.UpdateTab(
                            AppointmentsTab.entries[index]
                        ))
                    },
                    badges = badges
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
                AnimatedContent(targetState = uiState.currentTab, label = "") { targetTab ->
                    when(targetTab){
                        AppointmentsTab.UPCOMING -> {
                            AppointmentsLazyColumn(
                                reminders = upcoming,
                                onItemSelected = {
                                    onAppointmentAction(
                                        AppointmentsAction.OpenBottomSheet(
                                            reminder = it,
                                            bottomSheet = AppointmentBottomSheet.APPOINTMENT_DETAILS
                                        )
                                    )
                                }
                            )
                        }
                        AppointmentsTab.COMPLETED -> {
                            AppointmentsLazyColumn(
                                reminders = completed,
                                onItemSelected = {
                                    onAppointmentAction(
                                        AppointmentsAction.OpenBottomSheet(
                                            reminder = it,
                                            bottomSheet = AppointmentBottomSheet.APPOINTMENT_DETAILS
                                        )
                                    )
                                }
                            )
                        }
                        AppointmentsTab.MISSED -> {
                            AppointmentsLazyColumn(
                                reminders = missed,
                                onItemSelected = {
                                    onAppointmentAction(
                                        AppointmentsAction.OpenBottomSheet(
                                            reminder = it,
                                            bottomSheet = AppointmentBottomSheet.APPOINTMENT_DETAILS
                                        )
                                    )
                                }
                            )
                        }
                        AppointmentsTab.STOPPED -> {
                            AppointmentsLazyColumn(
                                reminders = stopped,
                                onItemSelected = {
                                    onAppointmentAction(
                                        AppointmentsAction.OpenBottomSheet(
                                            reminder = it,
                                            bottomSheet = AppointmentBottomSheet.APPOINTMENT_DETAILS
                                        )
                                    )
                                }
                            )
                        }

                        AppointmentsTab.UNSPECIFIED -> AppointmentsLazyColumn(
                            reminders = unspecified,
                            onItemSelected = {
                                onAppointmentAction(
                                    AppointmentsAction.OpenBottomSheet(
                                        reminder = it,
                                        bottomSheet = AppointmentBottomSheet.APPOINTMENT_DETAILS
                                    )
                                )
                            }
                        )
                    }
                }
                when(uiState.shownBottomSheet){
                    AppointmentBottomSheet.ADD_APPOINTMENT -> {
                        ExistingDoctorsBottomSheet(
                            doctors = existingDoctors,
                            onDismissRequest = {
                                scope.launch {
                                    existingDoctorsSheetState.hide()
                                    onAppointmentAction(
                                        AppointmentsAction.CloseBottomSheet
                                    )
                                }
                            },
                            sheetState = existingDoctorsSheetState,
                            query = query,
                            onQueryChange = {
                                onAddAppointmentsAction(
                                    AddAppointmentBottomSheetAction.UpdateQuery(
                                        it
                                    )
                                )
                            },
                            onClearButtonClick = {
                                AddAppointmentBottomSheetAction.UpdateQuery(
                                    ""
                                )
                            },
                            onDoctorItemClick ={
                                AddAppointmentBottomSheetAction.SelectDoctor(
                                    it
                                )
                            } ,
                            onDetailsButtonClick = {
                                AddAppointmentBottomSheetAction.ClickDetailsButton(
                                    it
                                )
                            }
                        )
                    }
                    AppointmentBottomSheet.APPOINTMENT_DETAILS ->{
                        uiState.selectedReminder?.let { reminder ->
                            AppointmentBottomSheet(
                                appointmentInfo = reminder,
                                sheetState = appointmentDetailsSheetState,
                                onDeleteButtonClick = {
                                    onAppointmentDetails(
                                        AppointmentDetailsBottomSheetAction.DeleteAppointment(it)
                                    )
                                },
                                onViewButtonClick ={
                                    onAppointmentDetails(
                                        AppointmentDetailsBottomSheetAction.ViewDetails(it)
                                    )
                                } ,
                                viewButtonText = R.string.view_details ,
                                deleteButtonText = R.string.delete,
                                onDismissRequest = {
                                    scope.launch {
                                        appointmentDetailsSheetState.hide()
                                        onAppointmentAction(
                                            AppointmentsAction.CloseBottomSheet
                                        )
                                    }

                                },
                                onMarkAsTakenButtonClick ={
                                    onAppointmentDetails(
                                        AppointmentDetailsBottomSheetAction.ChangeAppointmentState(
                                            appointment = it,
                                            newState = ReminderState.TAKEN
                                        )
                                    )
                                } ,
                                onMarkAsMissedButtonClick ={
                                    onAppointmentDetails(
                                        AppointmentDetailsBottomSheetAction.ChangeAppointmentState(
                                            appointment = it,
                                            newState = ReminderState.MISSED
                                        )
                                    )
                                } ,
                                onEditButtonClick = {
                                    onAppointmentDetails(
                                        AppointmentDetailsBottomSheetAction.EditAppointment(it)
                                    )
                                },
                                onStopReminderButtonClick = {
                                    onAppointmentDetails(
                                        AppointmentDetailsBottomSheetAction.ChangeAppointmentState(
                                            appointment = it,
                                            newState = ReminderState.STOPPED
                                        )
                                    )
                                }
                            )
                        }
                    }
                    AppointmentBottomSheet.CLOSE -> {}
                }


            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppointmentsScreenPreview() {
    AppointmentsScreen(
        onAppointmentAction = {},
        onAppointmentDetails = {},
        onAddAppointmentsAction = {},
        reminders = listOf(
            DoctorWithAppointment(doctor1, appointment1),
            DoctorWithAppointment(doctor2, appointment2),
        ),
        existingDoctors = listOf(doctor1, doctor2),
        query = "",
        uiState = AppointmentsUiState(),
        title = R.string.app_name
    )
}