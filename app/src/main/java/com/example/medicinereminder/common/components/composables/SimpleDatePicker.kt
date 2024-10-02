package com.example.medicinereminder.common.components.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.list_items.SimpleDatePickerItem
import com.example.medicinereminder.common.utility.extension.listFromRange
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SimpleDatePicker(
    modifier: Modifier = Modifier,
    startDate: LocalDate,
    endDate: LocalDate,
    selectedDate: LocalDate? = null,
    onDateSelected: (LocalDate) -> Unit,
    title: String? = null,
    onButtonClick: () -> Unit
) {
    val days = listFromRange(startDate,endDate)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        Text(
            text = title?: stringResource(id = R.string.pick_a_date),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(days) { day ->
                SimpleDatePickerItem(
                    date = day,
                    onDateSelected = onDateSelected,
                    isSelected = selectedDate == day
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = { onButtonClick()},
            colors = ButtonDefaults.outlinedButtonColors(),
        ) {
            Text(text = stringResource(R.string.use_calender))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SimpleDatePickerPreview() {
    MedicineReminderTheme {
        SimpleDatePicker(
            startDate =LocalDate.of(2024,8,28),
            endDate = LocalDate.of(2024,9,7),
            onDateSelected = {},
            onButtonClick = {}
        )
    }
}