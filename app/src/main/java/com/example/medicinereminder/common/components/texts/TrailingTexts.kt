package com.example.medicinereminder.common.components.texts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun TrailingTexts(
    modifier: Modifier = Modifier,
    firstText: String,
    secondText: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small6),
        horizontalAlignment = Alignment.End,
    ) {
        Text(
            text = firstText,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outlineVariant
        )
        Text(
            text = secondText,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TrailingTextsPreview() {
    MedicineReminderTheme {
        TrailingTexts(
            firstText = "3:30 AM",
            secondText = "3 Conflicts"
        )
    }
}