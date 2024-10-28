package com.example.medicinereminder.common.components.list_item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import com.example.medicinereminder.common.model.AppointmentTableItemInfo
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.helper.appointmentTableItems
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppointmentTableItem(
    modifier: Modifier = Modifier,
    appointmentTableItemInfo: AppointmentTableItemInfo,
    nextItemIsSelected: Boolean,
    onLongClick:(item:AppointmentTableItemInfo)->Unit,
    currentItem:AppointmentTableItemInfo,
) {
    val alpha =
        if (appointmentTableItemInfo.state == ReminderState.STOPPED)
            0.3f
        else 1f

    val backgroundColor =
        if (appointmentTableItemInfo.selected) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        }

    val dateTextColor =
        if (appointmentTableItemInfo.state != ReminderState.STOPPED) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurface
        }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor
            )
            .combinedClickable(
                onLongClick = {onLongClick(currentItem)},
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = ripple(),
                onClick = {},
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16)
                    .alpha(alpha),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = appointmentTableItemInfo.date,
                    style = MaterialTheme.typography.bodyMedium,
                    color = dateTextColor,
                    modifier = Modifier.weight(1.5f),
                )
                Text(
                    text = appointmentTableItemInfo.time,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = appointmentTableItemInfo.modifiedAt,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                )
            }
            if (!appointmentTableItemInfo.selected && !nextItemIsSelected) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.medium16)
                        .background(color = MaterialTheme.colorScheme.outlineVariant),
                )
            }
        }
    }
}


@DarkAndLightModePreview
@Composable
fun AppointmentTableItemSelectedPreview() {
    MedicineReminderTheme {
        Surface {
            AppointmentTableItem(
                appointmentTableItemInfo = appointmentTableItems[0],
                nextItemIsSelected = false,
                currentItem = appointmentTableItems[0],
                onLongClick = {},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun AppointmentTableItemPreview() {
    MedicineReminderTheme {
        Surface {
            AppointmentTableItem(
                appointmentTableItemInfo = appointmentTableItems[5],
                nextItemIsSelected = false,
                currentItem = appointmentTableItems[5],
                onLongClick = {},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun DisabledAppointmentTableItemPreview() {
    MedicineReminderTheme {
        Surface {
            AppointmentTableItem(
                appointmentTableItemInfo = appointmentTableItems[5],
                nextItemIsSelected = true,
                currentItem = appointmentTableItems[5],
                onLongClick = {},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun AppointmentTableItemStoppedPreview() {
    MedicineReminderTheme {
        Surface {
            AppointmentTableItem(
                appointmentTableItemInfo = appointmentTableItems[2],
                nextItemIsSelected = true,
                currentItem = appointmentTableItems[2],
                onLongClick = {},
            )
        }
    }
}

