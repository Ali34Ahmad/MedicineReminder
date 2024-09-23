package com.example.medicinereminder.common.components.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.R
import com.example.medicinereminder.common.ext.toLocalDate
import com.example.medicinereminder.presentation.ui.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    onConfirmClick: (date: LocalDate) -> Unit,
    onDismissRequest: () -> Unit,
    showDialog: Boolean,
    @StringRes confirmButtonText: Int,
    @StringRes dismissButtonText: Int,
    modifier: Modifier = Modifier,
    datePickerState: DatePickerState = rememberDatePickerState(),
) {
    val date by remember {
        derivedStateOf {
            datePickerState.selectedDateMillis?.toLocalDate() ?: LocalDate.now()
        }
    }
    if (showDialog) {
        DatePickerDialog(
            modifier = modifier
                .padding(MaterialTheme.spacing.medium16),
            onDismissRequest = onDismissRequest,
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmClick(date)
                        onDismissRequest()
                    }) {
                    Text(text = stringResource(id = confirmButtonText))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = stringResource(id = dismissButtonText))
                }
            },
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedDayContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    todayDateBorderColor = MaterialTheme.colorScheme.primary,
                    todayContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@DarkAndLightModePreview
@Composable
fun DatePickerDialogPreview() {
    MedicineReminderTheme {
        Surface {
            val datePickerState = rememberDatePickerState()
            var showDialog by remember {
                mutableStateOf(true)
            }
            var date by remember {
                mutableStateOf<LocalDate?>(null)
            }
            Button(onClick = {
                showDialog = true
            }) {
                Text(text = date.toString())
            }
            CustomDatePickerDialog(
                datePickerState = datePickerState,
                onConfirmClick = {
                    date = it
                    showDialog = false
                },
                onDismissRequest = {
                    showDialog = false
                },
                confirmButtonText = R.string.pick,
                dismissButtonText = R.string.cancel,
                showDialog = showDialog,
            )
        }
    }
}