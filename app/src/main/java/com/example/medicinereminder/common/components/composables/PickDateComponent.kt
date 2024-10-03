package com.example.medicinereminder.common.components.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.list_item.DaySocketHorizontalList
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing
import java.time.LocalDate

@Composable
fun PickDateComponent(
    modifier: Modifier = Modifier,
    startDate: LocalDate,
    endDate: LocalDate,
    selectedDate: LocalDate? = null,
    onDateSelected: (LocalDate) -> Unit,
    title: String? = null,
    onButtonClick: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        Text(
            text = title?: stringResource(id = R.string.pick_a_date),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
        DaySocketHorizontalList(
            startDate=startDate,
            endDate=endDate,
            selectedDate=selectedDate,
            onDateSelected=onDateSelected,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
        OutlinedButton(
            onClick = { onButtonClick()},
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
        PickDateComponent(
            startDate =LocalDate.of(2024,8,28),
            endDate = LocalDate.of(2024,9,7),
            onDateSelected = {},
            onButtonClick = {}
        )
    }
}