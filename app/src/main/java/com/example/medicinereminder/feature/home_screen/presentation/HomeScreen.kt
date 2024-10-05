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
import com.example.medicinereminder.common.components.top_app_bar.TopAppBarWithTitle
import com.example.medicinereminder.data.enums.ReminderType
import com.example.medicinereminder.data.local.entity.MedicineReminder
import com.example.medicinereminder.data.local.remindersInfo
import com.example.medicinereminder.data.model.ReminderInfo
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    uiState: HomeUIState,
    reminders: List<ReminderInfo>,
    tabStates: List<HomeTab>,
    onTabClick: (tab: HomeTab) -> Unit,
    onAddButtonClick: () -> Unit,
    onRefillButtonClick: () -> Unit,
    onReminderClick: (ReminderInfo) -> Unit,
    onMarkAsTakenButtonClick: (ReminderInfo) -> Unit,
    onMarkAsMissedButtonClick: (ReminderInfo) -> Unit,
    onEditButtonClick: (ReminderInfo) -> Unit,//I don't know what we will do here
    onStopReminderButtonClick: (ReminderInfo) -> Unit,
    onDeleteButtonClick: (ReminderInfo) -> Unit,
    onViewButtonClick: (ReminderInfo) -> Unit,
    onDismissRequest: () -> Unit
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
            TopAppBarWithTitle(
                showTrailingIcon = false,
                showNavigateUp = false,
                title = stringResource(id = title),
                onNavigateUpClick = {  },
                onTrailingIconClick = {  })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddButtonClick,
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
                onTabClick = { index -> onTabClick(tabStates[index]) },
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
                            onRefillButtonClick =onRefillButtonClick,
                            onItemSelected = onReminderClick,
                            sheetState = sheetState,
                            scope = scope
                        )
                    }
                    HomeTab.MEDICINES -> {
                        HomeLazyColumn(
                            reminders = medicineReminders,
                            isRefillCardShown = refills.isNotEmpty(),
                            onRefillButtonClick = onRefillButtonClick,
                            onItemSelected = onReminderClick,
                            sheetState = sheetState,
                            scope = scope
                        )
                    }
                    HomeTab.APPOINTMENTS -> {
                        HomeLazyColumn(
                            reminders = appointments,
                            isRefillCardShown = false,
                            onRefillButtonClick = onRefillButtonClick,
                            onItemSelected = onReminderClick,
                            sheetState = sheetState,
                            scope = scope
                        )
                    }
                    HomeTab.REFILL -> {
                        HomeLazyColumn(
                            reminders = refills,
                            isRefillCardShown = false,
                            onRefillButtonClick = onRefillButtonClick,
                            onItemSelected = onReminderClick,
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
                        onStopReminderButtonClick = onStopReminderButtonClick,
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
            onTabClick = { tab->
                uiState = uiState.copy(currentTab = tab)
                         },
            onRefillButtonClick = {
                uiState = uiState.copy(currentTab = HomeTab.REFILL)
            },
            onAddButtonClick = {

            },
            onReminderClick = { reminder ->
                uiState = uiState.copy(
                    selectedReminder = reminder,
                    isBottomSheetShown = !uiState.isBottomSheetShown
                )
            },
            onDeleteButtonClick = {},
            onEditButtonClick = {},
            onViewButtonClick = {},
            onStopReminderButtonClick = {},
            onMarkAsTakenButtonClick = {},
            onMarkAsMissedButtonClick = {},
            onDismissRequest = {
                uiState = uiState.copy(isBottomSheetShown = false)
            }
            )

    }
}