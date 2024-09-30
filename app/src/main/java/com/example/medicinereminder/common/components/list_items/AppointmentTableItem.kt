package com.example.medicinereminder.common.components.list_items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicinereminder.common.enums.AppointmentState
import com.example.medicinereminder.common.model.AppointmentTableItemInfo
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun AppointmentTableItem(
    modifier: Modifier = Modifier,
    appointmentTableItemInfo: AppointmentTableItemInfo
) {
    var alpha = 1f
    var backgroundColor = Color.Transparent
    when(appointmentTableItemInfo.state){
        AppointmentState.COMPLETED ->{
            backgroundColor = MaterialTheme.colorScheme.primaryContainer
        }
        AppointmentState.STOPPED -> {
            alpha = 0.5f
        }
        else ->{}
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .alpha(alpha),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = appointmentTableItemInfo.date,
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = appointmentTableItemInfo.time,
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
            Text(
                text = appointmentTableItemInfo.modifiedAt,
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
    }



@Preview(showBackground = true)
@Composable
fun PendingAppointmentTableItemPreview() {
    MedicineReminderTheme {
        AppointmentTableItem(
            appointmentTableItemInfo = AppointmentTableItemInfo(
                date = "Sep 10, 2024",
                time = "12:00 PM",
                modifiedAt = "Sep 5, 2024",
                state = AppointmentState.PENDING
            )
        )
    }
}
@Preview(showBackground = true)
@Composable
fun StoppedAppointmentTableItemPreview() {
    MedicineReminderTheme {
        AppointmentTableItem(
            appointmentTableItemInfo = AppointmentTableItemInfo(
                date = "Sep 10, 2024",
                time = "12:00 PM",
                modifiedAt = "Sep 5, 2024",
                state = AppointmentState.STOPPED
            )
        )
    }
}
@Preview(showBackground = true)
@Composable
fun CompletedAppointmentTableItemPreview() {
    MedicineReminderTheme {
        AppointmentTableItem(
            appointmentTableItemInfo = AppointmentTableItemInfo(
                date = "Sep 10, 2024",
                time = "12:00 PM",
                modifiedAt = "Sep 5, 2024",
                state = AppointmentState.COMPLETED
            )
        )
    }
}