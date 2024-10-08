package com.example.medicinereminder.feature.home_screen.presentation

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
import com.example.medicinereminder.common.components.top_app_bar.StandardTopAppBarComponent
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.enums.ReminderType
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.local.remindersInfo
import com.example.medicinereminder.data.model.ReminderInfo
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    uiState: HomeUIState,
    reminders: List<ReminderInfo>,
    tabStates: List<HomeTab>,
    onAction: (HomeAction) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(true)
    val scope = rememberCoroutineScope()
    val medicineReminders = reminders.filter {
        it.type == ReminderType.MEDICINE
    }
    val appointments = reminders.filter {
        it.type == ReminderType.APPOINTMENT
    }
    val refills = medicineReminders.filter {

        if(it.reminder !is MedicineReminder)
           return@filter false

        it.reminder.isRefillReminder
    }
    val badges = listOf(
        reminders.size,
        medicineReminders.size,
        appointments.size,
        refills.size
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            StandardTopAppBarComponent(
                showTrailingIcon = false,
                showNavigateUp = false,
                title = title,
                onNavigateUpClick = {  },
                onTrailingIconClick = {  })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAction(HomeAction.AddNewReminder)
                },
                shape = CircleShape,

            ) {
              Icon(
                  painter = painterResource(id = R.drawable.ic_add),
                  contentDescription = null
              )
            }
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
                    onAction(HomeAction.UpdateTeb(tabStates[index]))
                             },
                badges = badges
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
            AnimatedContent(
                targetState = uiState.currentTab,
                label = stringResource(id =R.string.home ),
            ) { targetState ->
                when(targetState){
                    HomeTab.ALL -> {
                        HomeLazyColumn(
                            reminders = reminders,
                            isRefillCardShown = refills.isNotEmpty(),
                            onRefillButtonClick ={
                                onAction(HomeAction.UpdateTeb(HomeTab.REFILL))
                            },
                            onItemSelected = {
                                onAction(HomeAction.UpdateReminder(it))
                            },
                            sheetState = sheetState,
                            scope = scope
                        )
                    }
                    HomeTab.MEDICINES -> {
                        HomeLazyColumn(
                            reminders = medicineReminders,
                            isRefillCardShown = refills.isNotEmpty(),
                            onRefillButtonClick = {
                                onAction(HomeAction.UpdateTeb(HomeTab.REFILL))
                            },
                            onItemSelected = {
                                onAction(HomeAction.UpdateReminder(it))
                            },
                            sheetState = sheetState,
                            scope = scope
                        )
                    }
                    HomeTab.APPOINTMENTS -> {
                        HomeLazyColumn(
                            reminders = appointments,
                            isRefillCardShown = false,
                            onRefillButtonClick = {
                                onAction(HomeAction.UpdateTeb(HomeTab.REFILL))
                            },
                            onItemSelected = {
                                onAction(HomeAction.UpdateReminder(it))
                            },
                            sheetState = sheetState,
                            scope = scope
                        )
                    }
                    HomeTab.REFILL -> {
                        HomeLazyColumn(
                            reminders = refills,
                            isRefillCardShown = false,
                            onRefillButtonClick = {  },
                            onItemSelected = {
                                onAction(HomeAction.UpdateReminder(it))
                            },
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
                        onMarkAsTakenButtonClick = {
                            onAction(HomeAction.UpdateReminderState(it,ReminderState.TAKEN))
                        },
                        onMarkAsMissedButtonClick = {
                            onAction(HomeAction.UpdateReminderState(it,ReminderState.MISSED))
                        },
                        onEditButtonClick = {
                            onAction(HomeAction.EditReminder(it))
                        },
                        onStopReminderButtonClick = {
                            onAction(HomeAction.UpdateReminderState(it,ReminderState.STOPPED))
                        },
                        onDeleteButtonClick = {
                            onAction(HomeAction.DeleteReminder(it))
                        },
                        onViewButtonClick = {
                            onAction(HomeAction.ViewDetails(it))
                        },
                        onDismissRequest = {
                            onAction(HomeAction.CloseBottomSheet)
                        }
                    )
                }
            }
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
            reminders = remindersInfo,
            tabStates = HomeTab.entries,
            uiState =uiState,
            onAction = {}
            )

    }
}