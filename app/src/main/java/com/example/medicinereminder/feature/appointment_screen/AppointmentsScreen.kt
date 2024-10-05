package com.example.medicinereminder.feature.appointment_screen

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
import com.example.medicinereminder.common.components.bottom_sheet.HomeBottomSheet
import com.example.medicinereminder.common.components.lazy_column.HomeLazyColumn
import com.example.medicinereminder.common.components.tab.ScrollableTab
import com.example.medicinereminder.common.components.top_app_bar.TopAppBarWithTitle
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.data.local.remindersInfo
import com.example.medicinereminder.data.model.ReminderInfo
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentsScreen(
    modifier: Modifier = Modifier,
    uiState: AppointmentsUiState,
    reminders: List<ReminderInfo>,
    @StringRes title:Int,
    tabs: List<AppointmentsTab>,
    onTabClick: (AppointmentsTab) -> Unit,
    onAppointmentClick: (ReminderInfo) ->Unit,
    onDeleteButtonClick: (ReminderInfo) -> Unit,
    onViewButtonClick: (ReminderInfo) -> Unit,
    onEditButtonClick: (ReminderInfo) -> Unit,
    onStopButtonClick: (ReminderInfo) -> Unit,
    onMarkAsTakenButtonClick: (ReminderInfo) -> Unit,
    onMarkAsMissedButtonClick: (ReminderInfo) -> Unit,
    onAddButtonClick: () -> Unit,
    onDismissRequest:() -> Unit,
    scope : CoroutineScope
) {
    val sheetState = rememberModalBottomSheetState(true)

    val upcomingAppointments = reminders.filter {
        it.reminder.reminderState == ReminderState.UPCOMING
    }
    val completedAppointment = reminders.filter {
        it.reminder.reminderState == ReminderState.TAKEN
    }
    val missedAppointments = reminders.filter {
        it.reminder.reminderState == ReminderState.MISSED || it.reminder.reminderState == ReminderState.UNSPECIFIED
    }

    val badges = listOf(
        upcomingAppointments.size,
        completedAppointment.size,
        missedAppointments.size
    )
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBarWithTitle(
                showTrailingIcon = false,
                showNavigateUp = false,
                title = stringResource(id = title),
                onNavigateUpClick = {  },
                onTrailingIconClick = {  })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick =onAddButtonClick,
                shape = CircleShape,
                ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null
                )
            }
        },
    ){ innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            ScrollableTab(
                itemsStringRes = tabs.map { it.title },
                showBadges = true,
                selectedItemIndex = uiState.currentTab.ordinal,
                onTabClick = { index -> onTabClick(tabs[index]) },
                badges = badges
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
            AnimatedContent(
                targetState = uiState.currentTab, label = ""
            ) { targetState ->
                when(targetState){
                    AppointmentsTab.UPCOMING -> HomeLazyColumn(
                        reminders = upcomingAppointments,
                        isRefillCardShown = false,
                        onRefillButtonClick = {  },
                        onItemSelected = onAppointmentClick,
                        sheetState = sheetState,
                        scope = scope
                    )
                    AppointmentsTab.COMPLETED -> {
                        HomeLazyColumn(
                            reminders = completedAppointment,
                            isRefillCardShown = false,
                            onRefillButtonClick = {  },
                            onItemSelected = onAppointmentClick,
                            sheetState = sheetState,
                            scope = scope
                        )
                    }
                    AppointmentsTab.MISSED -> {
                        HomeLazyColumn(
                            reminders = missedAppointments,
                            isRefillCardShown = false,
                            onRefillButtonClick = {  },
                            onItemSelected = onAppointmentClick,
                            sheetState = sheetState,
                            scope = scope
                        )
                    }
                }
            }
            uiState.selectedReminder?.let {
                if(uiState.isBottomSheetShown){
                    HomeBottomSheet(
                        reminder = uiState.selectedReminder,
                        sheetState = sheetState,
                        onMarkAsTakenButtonClick = onMarkAsTakenButtonClick,
                        onMarkAsMissedButtonClick = onMarkAsMissedButtonClick,
                        onEditButtonClick = onEditButtonClick,
                        onStopReminderButtonClick = onStopButtonClick,
                        onDeleteButtonClick = onDeleteButtonClick,
                        onViewButtonClick = onViewButtonClick,
                        onDismissRequest = onDismissRequest
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AppointmentsScreenPreview() {
    MedicineReminderTheme {
        var uiState by remember {
            mutableStateOf(AppointmentsUiState())
        }
        AppointmentsScreen(
            title = R.string.app_name,
            uiState =  uiState,
            reminders = remindersInfo.filter { it.reminder is Appointment },
            onAppointmentClick = {
                uiState = uiState.copy(selectedReminder = it, isBottomSheetShown = true)
            },
            onMarkAsTakenButtonClick = {},
            onDeleteButtonClick = {},
            onEditButtonClick = {},
            onTabClick = {
                uiState = uiState.copy(currentTab = it)
            },
            onMarkAsMissedButtonClick = {},
            onStopButtonClick = {},
            tabs = AppointmentsTab.entries,
            onAddButtonClick = {},
            scope = rememberCoroutineScope(),
            onDismissRequest = {
                uiState = uiState.copy(isBottomSheetShown = false)
            },
            onViewButtonClick = {}
        )
    }
}