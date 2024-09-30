package com.example.medicinereminder.core.components.list_items

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SimpleDatePickerItem(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isSelected: Boolean = false,
    onDateSelected: (LocalDate) -> Unit,
) {
    val dayNumber = date.dayOfMonth
    val dayOfWeek = date.dayOfWeek.name.substring(0, 3)
    val month = date.month.name.substring(0, 3)

    val selectedModifier = if (isSelected)
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
            .height(80.dp)
            .clickable {
                onDateSelected(date)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = month,
            style = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                color = textColor
            )
        )
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .then(selectedModifier)
            ,
            contentAlignment = Alignment.Center
        ){
            Text(
                text = dayNumber.toString(),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
        Text(
            text = dayOfWeek,
            style = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                color = textColor
            )
        )

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun UnselectedSimpleDatePickerItemPreview() {
    MedicineReminderTheme {
        SimpleDatePickerItem(
            date = LocalDate.of(2024,8,28),
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
        SimpleDatePickerItem(
            date = LocalDate.of(2024,8,31),
            isSelected = true,
            onDateSelected = {}
        )
    }
}
