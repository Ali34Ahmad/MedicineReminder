package com.example.medicinereminder.feature.add_appointment.component.time_picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.common.components.time_picker.CustomPicker
import com.example.medicinereminder.presentation.ui.theme.spacing
import com.example.medicinereminder.R

@Composable
fun CustomTimePicker(
    onHourSelected: (Int) -> Unit,
    onMinutesSelected: (Int) -> Unit,
    onPeriodSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val hours = MutableList(12){it+1}.toList()
    val minutes = MutableList(60){it}.toList()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.pick_time),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(MaterialTheme.spacing.large32))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            CustomPicker(
                items = hours,
                onItemSelected =onHourSelected
            )
            Spacer(Modifier.width(MaterialTheme.spacing.small8))
            Text(
                text = stringResource(R.string.hr),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(MaterialTheme.spacing.large36))
            CustomPicker(
                items = minutes,
                onItemSelected = onMinutesSelected
            )
            Spacer(Modifier.width(MaterialTheme.spacing.small8))
            Text(
                text = stringResource(R.string.min),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(MaterialTheme.spacing.large36))
            PeriodPicker(
                onPeriodSelected =onPeriodSelected
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CustomTimePickerPreview() {
    CustomTimePicker(
        {},{},{}
    )
}