package com.example.medicinereminder.feature.doctor_details.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.composables.AppointmentTable
import com.example.medicinereminder.common.components.composables.ContactInfoItem
import com.example.medicinereminder.common.components.tab.ScrollableTab
import com.example.medicinereminder.common.components.texts.TitleAndSubtitle
import com.example.medicinereminder.common.components.top_app_bar.DetailsTopAppBar
import com.example.medicinereminder.common.model.AppointmentTableItemInfo
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.feature.appointment_screen.presentation.appointments_main.AppointmentsTab
import com.example.medicinereminder.feature.doctor_details.component.composables.DoctorDetailsBottomBar
import com.example.medicinereminder.feature.doctor_details.component.composables.ShowDialogBox
import com.example.medicinereminder.presentation.ui.helper.appointmentTableItems
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorDetailsScreen(
    modifier: Modifier = Modifier,
    tableItems: List<AppointmentTableItemInfo>,
    uiState: DoctorDetailsUIState,
    onAction: (DoctorDetailsAction) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val isExpanded by remember {
        derivedStateOf {
            scrollBehavior.state.collapsedFraction == 0f
        }
    }

    val groupedItems = tableItems.groupBy { it.state }
    val taken = groupedItems[ReminderState.TAKEN] ?: emptyList()
    val missed = groupedItems[ReminderState.MISSED] ?: emptyList()
    val unspecified = groupedItems[ReminderState.UNSPECIFIED] ?: emptyList()
    val upcoming = groupedItems[ReminderState.UPCOMING] ?: emptyList()
    val stopped = groupedItems[ReminderState.STOPPED] ?: emptyList()
    val badges = listOf(
        upcoming.size,
        taken.size,
        missed.size,
        stopped.size,
        unspecified.size
    )
    val tabs = AppointmentsTab.entries.map { it.title }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DetailsTopAppBar(
                image = R.drawable.doctor_image,
                onNavigation = {
                    onAction(DoctorDetailsAction.NavigateBack)
                },
                onMenuIconClick = {
                    onAction(DoctorDetailsAction.OpenOptionsMenu)
                },
                scrollBehavior = scrollBehavior,
                isMenuIconShown = uiState.topAppBarState == TopAppBarState.INFO,
                actions = {
                    IconButton(
                        onClick = {
                            onAction(DoctorDetailsAction.ShowDeleteRemindersDialogBox)
                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete_outlined),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                    if (uiState.isEditButtonShown)
                        IconButton(
                            onClick = {
                                appointmentTableItems.find { it.selected }?.let { item ->
                                    onAction(
                                        DoctorDetailsAction.EditAppointment(
                                            item.appointmentId
                                        )
                                    )
                                }
                            }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_edit_outlined),
                                contentDescription = null,
                            )
                        }

                },
                content = {
                    when (uiState.topAppBarState) {
                        TopAppBarState.INFO -> {
                            Image(
                                painter = painterResource(id = R.drawable.doctor_image),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(
                                        MaterialTheme.sizing.large44
                                    )
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small8))
                            TitleAndSubtitle(
                                title = uiState.doctor.name,
                                subtitle = uiState.doctor.specialty,
                                isWarning = false
                            )
                        }

                        TopAppBarState.ACTIONS -> {
                            val selectedItems = tableItems.filter { it.selected }
                            Text(
                                text = selectedItems.size.toString(),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                },
                isOptionMenuExpanded = uiState.isOptionsMenuShown,
                onDelete = {
                    onAction(
                        DoctorDetailsAction.DeleteDoctor
                    )
                },
                onEdit = {
                    onAction(
                        DoctorDetailsAction.OpenExtraDetailsMenu
                    )
                },
                onOptionMenuDismissRequest = {
                    onAction(
                        DoctorDetailsAction.CloseOptionsMenu
                    )
                },
                isExtraMenuExpanded = uiState.isExtraDetailsMenuOpen,
                onDetailsButtonClick = {
                    onAction(
                        DoctorDetailsAction.EditDetails(doctorId = uiState.doctor.id)
                    )
                },
                onExtraMenuDismissRequest = {
                    onAction(
                        DoctorDetailsAction.CloseExtraDetailsMenu
                    )
                },
                onAllButtonClick = {
                    onAction(
                        DoctorDetailsAction.EditAll(doctorId = uiState.doctor.id)
                    )
                },
                onPhotoButtonClick = {
                    onAction(
                        DoctorDetailsAction.EditImage(doctorId = uiState.doctor.id)
                    )
                }
            )
        },
        bottomBar = {
            DoctorDetailsBottomBar(
                isStandard = !uiState.isMarkAsButtonShown,
                onStopButtonClick = {
                    onAction(
                        DoctorDetailsAction.ShowStopRemindersDialogBox
                    )
                },
                onBookButtonClick = {
                    onAction(
                        DoctorDetailsAction.BookAppointment(doctorId = uiState.doctor.id)
                    )
                },
                onMarkAsButtonClick = {
                    onAction(
                        DoctorDetailsAction.OpenMarkAsMenu
                    )
                },
                isMenuExpanded = uiState.isMarkAsMenuShown,
                onDismissRequest = {
                    onAction(
                        DoctorDetailsAction.HideMarkAsButton
                    )
                },
                onCompleteButtonClick = {
                    onAction(
                        DoctorDetailsAction.ChangeAppointmentsState(
                            newState = ReminderState.TAKEN
                        )
                    )
                },
                onMissedButtonClick = {
                    onAction(
                        DoctorDetailsAction.ChangeAppointmentsState(
                            newState = ReminderState.TAKEN
                        )
                    )
                }
            )

        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = MaterialTheme.spacing.medium24
                    )
                    .scrollable(
                        state = rememberScrollState(),
                        orientation = Orientation.Vertical
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Column {
                        //name and speciality section
                        Text(
                            modifier = Modifier.padding(
                                horizontal = MaterialTheme.spacing.medium16
                            ),
                            text = uiState.doctor.name,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
                        Text(
                            modifier = Modifier.padding(
                                horizontal = MaterialTheme.spacing.medium16
                            ),
                            text = uiState.doctor.specialty,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.surfaceContainerLow,
                            thickness = MaterialTheme.sizing.small8
                        )

                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
                        //contact info section
                        Text(
                            modifier = Modifier.padding(
                                horizontal = MaterialTheme.spacing.medium16
                            ),
                            text = stringResource(id = R.string.contact_info),
                            style = MaterialTheme.typography.titleMedium
                        )

                        uiState.doctor.phoneNumber?.let {
                            ContactInfoItem(
                                modifier = Modifier.padding(
                                    horizontal = MaterialTheme.spacing.medium16
                                ),
                                icon = R.drawable.ic_phone_outlined,
                                text = it,
                                onClick = {}
                            )
                        }
                        uiState.doctor.address?.let {
                            ContactInfoItem(
                                modifier = Modifier.padding(
                                    horizontal = MaterialTheme.spacing.medium16
                                ),
                                icon = R.drawable.ic_location_outlined,
                                text = it.toString(),
                                onClick = {}
                            )
                        }
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.surfaceContainerLow,
                            thickness = MaterialTheme.sizing.small8
                        )
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
                    }
                }
                //appointments table section
                ScrollableTab(
                    itemsStringRes = tabs,
                    showBadges = true,
                    selectedItemIndex = uiState.selectedTab.ordinal,
                    onTabClick = { index ->
                        onAction(
                            DoctorDetailsAction.UpdateTap(
                                AppointmentsTab.entries[index]
                            )
                        )
                    },
                    badges = badges
                )
                AnimatedContent(targetState = uiState.selectedTab, label = "") {
                    when (it) {
                        AppointmentsTab.UPCOMING -> {
                            AppointmentTable(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        MaterialTheme.spacing.medium16
                                    ),
                                tableItems = upcoming,
                                onItemLongClick = { index ->
                                    onAction(
                                        DoctorDetailsAction.UpdateTableItemSelection(
                                            index
                                        )
                                    )
                                }
                            )
                        }

                        AppointmentsTab.COMPLETED -> {
                            AppointmentTable(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        MaterialTheme.spacing.medium16
                                    ),
                                tableItems = taken,
                                onItemLongClick = { index ->
                                    onAction(
                                        DoctorDetailsAction.UpdateTableItemSelection(
                                            index
                                        )
                                    )
                                }
                            )
                        }

                        AppointmentsTab.MISSED -> {
                            AppointmentTable(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        MaterialTheme.spacing.medium16
                                    ),
                                tableItems = missed,
                                onItemLongClick = { index ->
                                    onAction(
                                        DoctorDetailsAction.UpdateTableItemSelection(
                                            index
                                        )
                                    )
                                }
                            )
                        }

                        AppointmentsTab.STOPPED -> {
                            AppointmentTable(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        MaterialTheme.spacing.medium16
                                    ),
                                tableItems = stopped,
                                onItemLongClick = { index ->
                                    onAction(
                                        DoctorDetailsAction.UpdateTableItemSelection(
                                            index
                                        )
                                    )
                                }
                            )
                        }

                        AppointmentsTab.UNSPECIFIED -> {
                            AppointmentTable(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        MaterialTheme.spacing.medium16
                                    ),
                                tableItems = unspecified,
                                onItemLongClick = { index ->
                                    onAction(
                                        DoctorDetailsAction.UpdateTableItemSelection(
                                            index
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
           ShowDialogBox(
               state = uiState.showingDialogBox,
               doctorName = uiState.doctor.name,
               onAction = onAction,
               appointmentNumber = tableItems.filter { it.selected }.size
           )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorScreenPreview() {
    MedicineReminderTheme {
        DoctorDetailsScreen(
            tableItems = appointmentTableItems,
            uiState = DoctorDetailsUIState(),
            onAction = {

            }
        )
    }
}