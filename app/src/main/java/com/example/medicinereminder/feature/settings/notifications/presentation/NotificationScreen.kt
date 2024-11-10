package com.example.medicinereminder.feature.settings.notifications.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.list_item.ListItemWithDescriptionAndTrailingTextButton
import com.example.medicinereminder.common.components.list_item.ListItemWithSwitch
import com.example.medicinereminder.common.components.slider.SliderComponent
import com.example.medicinereminder.common.components.top_app_bar.StandardTopAppBarComponent
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun NotificationScreen(
    onAction: (NotificationAction) -> Unit,
    uiState: NotificationUIState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            StandardTopAppBarComponent(
                title = R.string.notifications,
                showNavigateUp = true,
                onNavigateUpClick = {
                    onAction(
                        NotificationAction.NavigateUp
                    )
                },
                showTrailingIcon = false,
                )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.small4)
                    .scrollable(
                        rememberScrollState(),
                        orientation = Orientation.Vertical
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.sound),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = MaterialTheme.spacing.medium16)
                )
                Spacer(Modifier.height(MaterialTheme.spacing.small4))
                ListItemWithSwitch(
                    modifier = Modifier.fillMaxWidth(),
                    title = R.string.vibrate,
                    checked = uiState.isVibrationActivated,
                    onCheckedChange = {
                        onAction(
                            NotificationAction.ToggleVibration
                        )
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
                ListItemWithDescriptionAndTrailingTextButton(
                    title = R.string.ringtone,
                    subTitle = uiState.ringtone,
                    buttonText = R.string.change,
                    onClick = {
                        onAction(
                            NotificationAction.ShowRingtoneList
                        )
                    },
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
                Spacer(Modifier.height(MaterialTheme.spacing.medium16))
                SliderComponent(
                    value = uiState.soundVolume,
                    onValueChange = {
                        onAction(
                            NotificationAction.ChangeSoundVolume(it)
                        )
                    }
                )
                Spacer(Modifier.height(MaterialTheme.spacing.medium24))
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = MaterialTheme.sizing.small8,
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
                //Medicine Reminder section
                Spacer(Modifier.height(MaterialTheme.spacing.small8))
                Text(
                    text = stringResource(R.string.medicine_reminder_content),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = MaterialTheme.spacing.medium16)
                )
                Spacer(Modifier.height(MaterialTheme.spacing.small4))
                ListItemWithSwitch(
                    title = R.string.show_medicine_image,
                    checked = uiState.isMedicineImageVisible,
                    onCheckedChange = {
                        onAction(
                            NotificationAction.ToggleMedicineImageVisibility
                        )
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
                ListItemWithSwitch(
                    title = R.string.show_medicine_conflicts,
                    checked = uiState.isMedicineConflictsVisible,
                    onCheckedChange = {
                        onAction(
                            NotificationAction.ToggleMedicineConflictsVisibility
                        )
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
                ListItemWithSwitch(
                    title = R.string.show_medicine_notes,
                    checked = uiState.isMedicineNotesVisible,
                    onCheckedChange = {
                        onAction(
                            NotificationAction.ToggleMedicineNotesVisibility
                        )
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
                ListItemWithSwitch(
                    title = R.string.show_remaining_time,
                    checked = uiState.isMedicineRemainingTimeVisible,
                    onCheckedChange = {
                        onAction(
                            NotificationAction.ToggleMedicineRemainingTimeVisibility
                        )
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = MaterialTheme.sizing.small8,
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )

                //Appointment reminder content
                Spacer(Modifier.height(MaterialTheme.spacing.small8))
                Text(
                    text = stringResource(R.string.appointment_reminder_content),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = MaterialTheme.spacing.medium16)
                )
                Spacer(Modifier.height(MaterialTheme.spacing.small4))
                ListItemWithSwitch(
                    title = R.string.show_doctor_image,
                    checked = uiState.isDoctorImageVisible,
                    onCheckedChange = {
                        onAction(
                            NotificationAction.ToggleDoctorImageVisibility
                        )
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
                ListItemWithSwitch(
                    title = R.string.show_remaining_time,
                    checked = uiState.isAppointmentRemainingTimeVisible,
                    onCheckedChange = {
                        onAction(
                            NotificationAction.ToggleAppointmentRemainingTimeVisibility
                        )
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = MaterialTheme.spacing.small8,
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
                //Reminder behavior
                Spacer(Modifier.height(MaterialTheme.spacing.small8))
                Text(
                    text = stringResource(R.string.reminder_behavior),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = MaterialTheme.spacing.medium16)
                )
                Spacer(Modifier.height(MaterialTheme.spacing.small4))
                ListItemWithSwitch(
                    title = R.string.wake_device_for_reminders,
                    checked = uiState.isRemindersActivated,
                    onCheckedChange = {
                        onAction(
                            NotificationAction.ToggleReminderActivation
                        )
                    }
                )
                //It will be visible when you activate the reminders
                AnimatedVisibility(
                    visible = uiState.isRemindersActivated,
                    label = ""
                ) {
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.surfaceContainerLow
                    )
                    ListItemWithSwitch(
                        title = R.string.repeats,
                        checked = uiState.isRepetitionActivated,
                        onCheckedChange = {
                            onAction(
                                NotificationAction.ToggleRepeatsActivation
                            )
                        }
                    )
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.surfaceContainerLow
                    )
                }
                //it will be visible when you activate the reminders and repeats
                AnimatedVisibility(
                    visible = uiState.isRemindersActivated && uiState.isRepetitionActivated
                ) {
                    ListItemWithDescriptionAndTrailingTextButton(
                        title = R.string.number_of_repeats,
                        subTitle = uiState.numberOfRepeats.toString(),
                        buttonText = R.string.change,
                        onClick = {
                            onAction(
                                NotificationAction.ShowNumberOfRepeatsList
                            )
                        },
                    )
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.surfaceContainerLow
                    )
                    ListItemWithDescriptionAndTrailingTextButton(
                        title = R.string.repeat_interval,
                        subTitle = uiState.repeatInterval.toString(),
                        buttonText = R.string.change,
                        onClick = {
                            onAction(
                                NotificationAction.ShowRepeatIntervalsList
                            )
                        },
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    MedicineReminderTheme {
        val uiState = remember {
            mutableStateOf(NotificationUIState())
        }
        NotificationScreen(
            onAction = { action ->
                when(action){
                    is NotificationAction.ChangeNumberOfRepeats -> {
                        uiState.value = uiState.value.copy(numberOfRepeats = action.numberOfRepeats)
                    }
                    is NotificationAction.ChangeRingtone -> {
                        uiState.value = uiState.value.copy(ringtone = action.ringtone)
                    }
                    is NotificationAction.ChangeSoundVolume -> {
                        uiState.value = uiState.value.copy(soundVolume = action.volume)
                    }
                    NotificationAction.ShowNumberOfRepeatsList -> {
                        uiState.value = uiState.value.copy(isNumberOfRepeatsLisVisible = true)
                    }
                    NotificationAction.ShowRepeatIntervalsList -> {
                        uiState.value = uiState.value.copy(isRepeatIntervalsListVisible = true)
                    }
                    NotificationAction.ShowRingtoneList ->{
                        uiState.value = uiState.value.copy(isRingtonesListVisible = true)
                    }
                    NotificationAction.ToggleReminderActivation -> {
                        uiState.value = uiState.value.copy(isRemindersActivated = !uiState.value.isRemindersActivated)
                    }
                    NotificationAction.ToggleRepeatsActivation -> {
                        uiState.value = uiState.value.copy(isRepetitionActivated = !uiState.value.isRepetitionActivated)
                    }
                    NotificationAction.HideRingtoneList -> {
                        uiState.value = uiState.value.copy(isRingtonesListVisible = false)
                    }
                    NotificationAction.HideRepeatIntervalsList -> {
                        uiState.value = uiState.value.copy(isNumberOfRepeatsLisVisible = false)
                    }
                    NotificationAction.HideNumberOfRepeatsList -> {
                        uiState.value = uiState.value.copy(isNumberOfRepeatsLisVisible = false)
                    }
                    else -> {}
                }

            },
            uiState = uiState.value,
        )
    }
}









