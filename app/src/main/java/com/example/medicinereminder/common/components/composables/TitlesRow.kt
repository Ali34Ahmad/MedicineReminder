package com.example.medicinereminder.common.components.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.constants.appointmentsTableTitles
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun TitlesRow(
    modifier: Modifier = Modifier,
    @StringRes vararg titles: Int,
    firstItemIsSelected: Boolean,
) {
    Column {
        Row(
            modifier = modifier
                .padding(MaterialTheme.spacing.medium16)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            titles.forEachIndexed { index, title ->
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.weight(if (index == 0) 1.5f else 1f),
                    textAlign = if (index == titles.lastIndex) TextAlign.End else TextAlign.Start,
                )
            }
        }
        if (!firstItemIsSelected) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium16)
                    .background(color = MaterialTheme.colorScheme.outlineVariant),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TitlesRowPreview() {
    MedicineReminderTheme {
        TitlesRow(
            titles = appointmentsTableTitles.toIntArray(),
            firstItemIsSelected = false,
        )
    }
}

@DarkAndLightModePreview
@Composable
fun TitlesRowWithFirstItemSelectedPreview() {
    MedicineReminderTheme {
        TitlesRow(
            titles = appointmentsTableTitles.toIntArray(),
            firstItemIsSelected = true,
        )
    }
}
