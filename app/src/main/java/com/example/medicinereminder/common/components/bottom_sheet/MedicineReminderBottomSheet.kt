package com.example.medicinereminder.common.components.bottom_sheet

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.list_item.AppointmentItem
import com.example.medicinereminder.common.components.list_item.BottomSheetItem
import com.example.medicinereminder.common.model.BottomSheetInfo
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineReminderBottomSheet(
    modifier: Modifier = Modifier,
    topContent: @Composable () -> Unit,
    actions: List<BottomSheetInfo>,
    onDelete: () -> Unit,
    onView: () -> Unit,
    onDismissRequest: () -> Unit,
    @StringRes deleteText:Int,
    @StringRes viewText: Int
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
    ) {
        topContent()
        actions.forEach{
            BottomSheetItem(
                data = it,
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.small1)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    MaterialTheme.spacing.medium16
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onDelete,
                border = BorderStroke(
                    width = MaterialTheme.spacing.small1,
                    color = MaterialTheme.colorScheme.error
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(text = stringResource(id = deleteText))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onView,
            modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = viewText),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MedicineReminderBottomSheetPreview() {
    MedicineReminderTheme {
        MedicineReminderBottomSheet(
            topContent = { AppointmentItem(doctorName = "Ali Mansoura", speciality = "Eyes", time ="12:00 PM" ) },
            actions = listOf(
                BottomSheetInfo(
                    icon = R.drawable.ic_check,
                    text = R.string.mark_as_taken,
                    onClick = {}
                ),
                BottomSheetInfo(
                    icon = R.drawable.ic_notification_missed_outlined,
                    text = R.string.mark_as_missed,
                    onClick = {}
                ),
                BottomSheetInfo(
                    icon = R.drawable.ic_edit_outlined,
                    text = R.string.edit_date_and_time,
                    onClick = {}
                ),
                BottomSheetInfo(
                    icon = R.drawable.ic_notification_off_outlined,
                    text = R.string.stop_reminder,
                    onClick = {}
                )
            ),
            onView = {},
            onDelete = {},
            deleteText = R.string.delete_medicine,
            viewText = R.string.view_details,
            onDismissRequest = {}
        )
    }
}

