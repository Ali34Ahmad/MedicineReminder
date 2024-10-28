package com.example.medicinereminder.feature.home_screen.presentation.home_main

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.bottom_sheet.AppointmentBottomSheet
import com.example.medicinereminder.common.components.bottom_sheet.MedicineReminderBottomSheet
import com.example.medicinereminder.common.components.lazy_column.HomeLazyColumn
import com.example.medicinereminder.common.components.tab.ScrollableTab
import com.example.medicinereminder.common.components.top_app_bar.StandardTopAppBarComponent
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.local.timedEvents
import com.example.medicinereminder.data.model.DoctorWithAppointment
import com.example.medicinereminder.data.model.MedicineWithReminder
import com.example.medicinereminder.data.model.TimedEvent
import com.example.medicinereminder.feature.home_screen.presentation.appointment_bottom_sheet.AppointmentBottomSheetAction
import com.example.medicinereminder.feature.home_screen.presentation.reminder_bottom_sheet.MedicineReminderBottomSheetAction
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    uiState: HomeUIState,
    reminders: List<TimedEvent>,
    tabStates: List<HomeTab>,
    onHomeAction: (HomeAction) -> Unit,
    onAppointmentBottomSheetAction: (AppointmentBottomSheetAction) -> Unit,
    onReminderBottomSheetAction: (MedicineReminderBottomSheetAction) -> Unit,
) {
    val appointmentSheetState = rememberModalBottomSheetState()
    val medicineReminderSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val medicinesReminders = reminders.filterIsInstance<MedicineWithReminder>()
    val doctorsAppointments = reminders.filterIsInstance<DoctorWithAppointment>()
    val refills = medicinesReminders.filter { it.reminder.isRefillReminder }

    val badges = listOf(
        reminders.size,
        medicinesReminders.size,
        doctorsAppointments.size,
        refills.size
    )
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            StandardTopAppBarComponent(
                showTrailingIcon = false,
                showNavigateUp = false,
                title = title,
                onNavigateUpClick = { },
                onTrailingIconClick = { })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            ScrollableTab(
                itemsStringRes = tabStates.map { it.title },
                showBadges = true,
                selectedItemIndex = uiState.currentTab.ordinal,
                onTabClick = { index ->
                    onHomeAction(HomeAction.UpdateTeb(tabStates[index]))
                },
                badges = badges
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
            AnimatedContent(
                targetState = uiState.currentTab,
                label = stringResource(id = R.string.home),
            ) { targetTab ->
                when (targetTab) {
                    HomeTab.ALL -> {
                        HomeLazyColumn(
                            reminders = reminders,
                            isRefillCardShown = refills.isNotEmpty(),
                            onRefillButtonClick = {
                                onHomeAction(HomeAction.UpdateTeb(HomeTab.REFILL))
                            },
                            onItemSelected = {
                                val bottomSheet = if (it is DoctorWithAppointment)
                                    HomeBottomSheet.APPOINTMENT else HomeBottomSheet.REMINDER
                                onHomeAction(
                                    HomeAction.OpenBottomSheet(
                                        bottomSheet, it
                                    )
                                )
                            },
                        )
                    }

                    HomeTab.MEDICINES -> {
                        HomeLazyColumn(
                            reminders = medicinesReminders,
                            isRefillCardShown = refills.isNotEmpty(),
                            onRefillButtonClick = {
                                onHomeAction(HomeAction.UpdateTeb(HomeTab.REFILL))
                            },
                            onItemSelected = {
                                onHomeAction(
                                    HomeAction.OpenBottomSheet(
                                        HomeBottomSheet.REMINDER,
                                        it
                                    )
                                )
                            },
                        )
                    }

                    HomeTab.APPOINTMENTS -> {
                        HomeLazyColumn(
                            reminders = doctorsAppointments,
                            isRefillCardShown = false,
                            onRefillButtonClick = {
                                onHomeAction(HomeAction.UpdateTeb(HomeTab.REFILL))
                            },
                            onItemSelected = {
                                onHomeAction(
                                    HomeAction.OpenBottomSheet(
                                        HomeBottomSheet.APPOINTMENT,
                                        it
                                    )
                                )
                            }
                        )
                    }

                    HomeTab.REFILL -> {
                        HomeLazyColumn(
                            reminders = refills,
                            isRefillCardShown = false,
                            onRefillButtonClick = {
                                onHomeAction(HomeAction.UpdateTeb(HomeTab.REFILL))
                            },
                            onItemSelected = {
                                onHomeAction(
                                    HomeAction.OpenBottomSheet(
                                        HomeBottomSheet.REMINDER,
                                        it
                                    )
                                )
                            },
                        )
                    }
                }
            }

        }
        when (uiState.shownBottomSheet) {
            HomeBottomSheet.APPOINTMENT -> {
                if (uiState.selectedReminder is DoctorWithAppointment) {
                    val appointment = uiState.selectedReminder.appointment
                    AppointmentBottomSheet(
                        appointmentInfo = uiState.selectedReminder,
                        sheetState = appointmentSheetState,
                        onDeleteButtonClick = {
                            onAppointmentBottomSheetAction(
                                AppointmentBottomSheetAction.DeleteAppointment(appointment)
                            )
                        },
                        onViewButtonClick = {
                            onAppointmentBottomSheetAction(
                                AppointmentBottomSheetAction.ViewAppointmentDetails(
                                    appointment
                                )
                            )
                        },
                        viewButtonText = R.string.view_details,
                        deleteButtonText = R.string.delete,
                        onDismissRequest = {
                            scope.launch {
                                appointmentSheetState.hide()
                            }
                            onHomeAction(HomeAction.CloseBottomSheet)
                        },
                        onMarkAsTakenButtonClick = {
                            onAppointmentBottomSheetAction(
                                AppointmentBottomSheetAction.ChangeAppointmentState(
                                    appointment,
                                    ReminderState.TAKEN
                                )
                            )
                        },
                        onMarkAsMissedButtonClick = {
                            onAppointmentBottomSheetAction(
                                AppointmentBottomSheetAction.ChangeAppointmentState(
                                    appointment,
                                    ReminderState.MISSED
                                )
                            )
                        },
                        onEditButtonClick = {
                            onAppointmentBottomSheetAction(
                                AppointmentBottomSheetAction.EditAppointment(
                                    appointment
                                )
                            )
                        }
                    ) {

                    }
                }
            }

            HomeBottomSheet.REMINDER -> {
                if (uiState.selectedReminder is MedicineWithReminder) {
                    val medicineReminder = uiState.selectedReminder.reminder
                    MedicineReminderBottomSheet(
                        reminderInfo = uiState.selectedReminder,
                        sheetState = medicineReminderSheetState,
                        onDeleteButtonClick = {
                            onReminderBottomSheetAction(
                                MedicineReminderBottomSheetAction.DeleteMedicineReminder(
                                    medicineReminder
                                )
                            )
                        },
                        onViewButtonClick = {
                            onReminderBottomSheetAction(
                                MedicineReminderBottomSheetAction.ViewMedicineReminderDetails(
                                    medicineReminder
                                )
                            )
                        },
                        viewButtonText = R.string.view_details,
                        deleteButtonText = R.string.delete,
                        onDismissRequest = {
                            scope.launch {
                                medicineReminderSheetState.hide()
                            }
                            onHomeAction(HomeAction.CloseBottomSheet)
                        },
                        onMarkAsTakenButtonClick = {
                            onReminderBottomSheetAction(
                                MedicineReminderBottomSheetAction.ChangeMedicineReminderState(
                                    medicineReminder,
                                    ReminderState.TAKEN
                                )
                            )
                        },
                        onMarkAsMissedButtonClick = {
                            onReminderBottomSheetAction(
                                MedicineReminderBottomSheetAction.ChangeMedicineReminderState(
                                    medicineReminder,
                                    ReminderState.MISSED
                                )
                            )
                        },
                        onEditButtonClick = {
                            onReminderBottomSheetAction(
                                MedicineReminderBottomSheetAction.EditMedicineReminder(
                                    medicineReminder
                                )
                            )
                        }
                    ) {

                    }
                }
            }

            else -> {}
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MedicineReminderTheme {

        var uiState by remember {
            mutableStateOf(
                HomeUIState()
            )
        }
        HomeScreen(
            title = R.string.app_name,
            tabStates = HomeTab.entries,
            uiState = uiState,
            onHomeAction = {
            },
            onAppointmentBottomSheetAction = {},
            onReminderBottomSheetAction = {},
            reminders = timedEvents.sortedBy { it.dateTime }
        )
    }
}