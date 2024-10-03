package com.example.medicinereminder.common.components.texts

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun TitleAndTime(
    modifier: Modifier = Modifier,
    title: String,
    time:String,
) {
    Row(
        modifier = modifier,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
        )

        Text(
            text = time,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@DarkAndLightModePreview
@Composable
fun TitleDescPreview() {
    MedicineReminderTheme {
        Surface {
            TitleAndTime(title = "Vitamin D", time = "12:30 PM")
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TitleDescWarningPreview() {
    MedicineReminderTheme {
        Surface {
            TitleAndTime(title = "Vitamin D", time = "12:30 PM")
        }
    }
}