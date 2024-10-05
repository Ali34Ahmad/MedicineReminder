package com.example.medicinereminder.common.components.texts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun TitleAndSubtitle(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    isWarning:Boolean
) {
    val subtitleColor = if(isWarning) MaterialTheme.colorScheme.error
    else MaterialTheme.colorScheme.onSurfaceVariant
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.height(MaterialTheme.spacing.medium24)
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.titleMedium,
            color = subtitleColor,
            modifier = Modifier.height(MaterialTheme.spacing.medium24)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitleAndSubtitlePreview() {
    MedicineReminderTheme {
        TitleAndSubtitle(
            title = "Vitamin C",
            subtitle = "1 Pill",
            isWarning = false
        )
    }
}
@Preview(showBackground = true)
@Composable
fun WarningTitleAndSubtitlePreview() {
    MedicineReminderTheme {
        TitleAndSubtitle(
            title = "Vitamin C",
            subtitle = "Out of stock",
            isWarning = true
        )
    }
}