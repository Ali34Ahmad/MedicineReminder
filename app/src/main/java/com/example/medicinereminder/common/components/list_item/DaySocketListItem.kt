package com.example.medicinereminder.common.components.list_item

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing
import androidx.compose.ui.unit.sp
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import java.time.LocalDate


@Composable
fun DaySocketListItem(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isSelected: Boolean = false,
    onDateSelected: (LocalDate) -> Unit,
) {
    val dayNumber = date.dayOfMonth
    val dayOfWeek = date.dayOfWeek.name.substring(0, 3)
    val month = date.month.name.substring(0, 3)

    val dayNumberModifier = if (isSelected)
        Modifier.background(MaterialTheme.colorScheme.primaryContainer)
    else
        Modifier.border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant,
            shape = CircleShape
        )
    val textColor = if (isSelected)
        MaterialTheme.colorScheme.primary
    else
        MaterialTheme.colorScheme.onSurfaceVariant

    Column(
        modifier = modifier
            .clickable {
                onDateSelected(date)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
    ) {
        Text(
            text = month,
            style = MaterialTheme.typography.bodySmall,
            color = textColor,
        )
        Box(
            modifier = Modifier
                .size(MaterialTheme.sizing.large32)
                .clip(CircleShape)
                .then(dayNumberModifier),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = dayNumber.toString(),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        Text(
            text = dayOfWeek,
            style = MaterialTheme.typography.bodySmall,
            color = textColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnselectedSimpleDatePickerItemPreview() {
    MedicineReminderTheme {
        DaySocketListItem(
            date = LocalDate.of(2024, 8, 28),
            isSelected = false,
            onDateSelected = {}
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SelectedSimpleDatePickerItemPreview() {
    MedicineReminderTheme {
        DaySocketListItem(
            date = LocalDate.of(2024, 8, 31),
            isSelected = true,
            onDateSelected = {}
        )
    }
}
