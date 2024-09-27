package com.example.medicinereminder.core.components.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.core.components.list_items.AppointmentTableItem
import com.example.medicinereminder.core.enums.AppointmentState
import com.example.medicinereminder.core.model.AppointmentTableItemInfo
import com.example.medicinereminder.ui.theme.MedicineReminderTheme

@Composable
fun AppointmentTable(
    modifier: Modifier = Modifier,
    tableItems: List<AppointmentTableItemInfo> = emptyList()
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TitlesRow(
            titles = arrayOf("Date","Time","Modified at"),
        )
        tableItems.forEach { item ->
            HorizontalDivider()
            AppointmentTableItem(
                appointmentTableItemInfo = item
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppointmentTablePreview() {
    MedicineReminderTheme {
        AppointmentTable(
            tableItems = listOf(
                AppointmentTableItemInfo(
                    date = "Sep 10, 2024",
                    time = "12:00 PM",
                    modifiedAt = "Sep 5, 2024",
                    state = AppointmentState.COMPLETED
                ),
                AppointmentTableItemInfo(
                    date = "Sep 10, 2024",
                    time = "12:00 PM",
                    modifiedAt = "Sep 5, 2024",
                    state = AppointmentState.PENDING
                ),
                AppointmentTableItemInfo(
                    date = "Sep 10, 2024",
                    time = "12:00 PM",
                    modifiedAt = "Sep 5, 2024",
                    state = AppointmentState.STOPPED
                ),
                AppointmentTableItemInfo(
                    date = "Sep 10, 2024",
                    time = "12:00 PM",
                    modifiedAt = "Sep 5, 2024",
                    state = AppointmentState.PENDING
                )
            )
        )
    }
}