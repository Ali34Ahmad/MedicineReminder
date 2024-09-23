package com.example.medicinereminder.common.components.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.presentation.ui.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun TabItem(
    text: String,
    selected: Boolean,
    index: Int,
    onClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Tab(
        selected = selected,
        onClick = { onClick(index) },
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.medium16,
                vertical = MaterialTheme.spacing.medium16
            )
        )
    }
}

@DarkAndLightModePreview
@Composable
fun TabItemPreview() {
    MedicineReminderTheme {
        Surface {
            TabItem(
                text = "Text",
                selected = true,
                onClick = {},
                index = 1
            )
        }
    }
}
