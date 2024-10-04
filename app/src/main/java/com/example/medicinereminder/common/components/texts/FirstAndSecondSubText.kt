package com.example.medicinereminder.common.components.texts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.medicinereminder.R
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun FirstAndSecondSubText(
    modifier: Modifier = Modifier,
    firstText: String,
    secondText: String? = null,
    isWarning: Boolean = false,
    trailingIcon: Int? = null,
    reminderState: ReminderState,
) {
    Row(
        modifier = modifier
    ) {
        val color = if (isWarning)
            MaterialTheme.colorScheme.error
        else
            MaterialTheme.colorScheme.onSurfaceVariant

        Text(
            text = firstText,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            color = color,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.small4),
        )

        if (reminderState != ReminderState.UPCOMING) {
            trailingIcon?.let {
                Icon(
                    painter = painterResource(id = trailingIcon),
                    contentDescription = null,
                )
            }
        } else {
            secondText?.let {
                Text(
                    text = secondText,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = MaterialTheme.spacing.small4)
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TrailerTextPreview() {
    MaterialTheme {
        FirstAndSecondSubText(
            firstText = "1 Pill",
            secondText = "3 conflicts",
            reminderState = ReminderState.UPCOMING
        )
    }
}

@DarkAndLightModePreview
@Composable
fun TrailerTextUpcomingPreview() {
    MaterialTheme {
        FirstAndSecondSubText(
            firstText = "1 Pill",
            secondText = "3 conflicts",
            reminderState = ReminderState.UPCOMING
        )
    }
}

@DarkAndLightModePreview
@Composable
fun TrailerTextMissedPreview() {
    MaterialTheme {
        FirstAndSecondSubText(
            firstText = "1 Pill",
            secondText = "3 conflicts",
            reminderState = ReminderState.MISSED
        )
    }
}

@DarkAndLightModePreview
@Composable
fun TrailerTextTakenPreview() {
    MaterialTheme {
        FirstAndSecondSubText(
            firstText = "1 Pill",
            secondText = "3 conflicts",
            reminderState = ReminderState.TAKEN,
        )
    }
}


@DarkAndLightModePreview
@Composable
fun TrailerTextUnspecifiedPreview() {
    MaterialTheme {
        FirstAndSecondSubText(
            firstText = "1 Pill",
            secondText = "3 conflicts",
            reminderState = ReminderState.UNSPECIFIED,
        )
    }
}

