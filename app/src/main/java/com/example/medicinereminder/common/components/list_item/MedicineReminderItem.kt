package com.example.medicinereminder.common.components.list_item

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.texts.TitleAndSubtitle
import com.example.medicinereminder.common.components.texts.TrailingTexts
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun MedicineReminderItem(
    modifier: Modifier = Modifier,
    state: ReminderState,
    medicineName: String,
    subtitle: String,
    time: String,
    conflictsNumber: Int,
    image: String?,
    onClick: () -> Unit,
    @DrawableRes defaultImage: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(
                horizontal = MaterialTheme.spacing.medium16,
                vertical = MaterialTheme.spacing.medium12
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
                modifier = Modifier
                    .size(
                        MaterialTheme.sizing.large52
                    )
                    .clip(shape = CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceContainerLow)
                    .padding(MaterialTheme.spacing.small6),
                painter = painterResource(id = defaultImage),
                contentDescription = null,
            )
        
        TitleAndSubtitle(
            modifier = Modifier.weight(1f)
                .padding(start = MaterialTheme.spacing.medium16),
            title = medicineName,
            subtitle = subtitle,
            isWarning = false
        )
        when (state) {
            ReminderState.UPCOMING -> {
                TrailingTexts(
                    firstText = time,
                    secondText = stringResource(id = R.string.conflicts,conflictsNumber)
                )
            }
            ReminderState.UNSPECIFIED ->{
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock_outlined),
                    contentDescription = null,
                )
            }
            ReminderState.TAKEN ->{
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                )
            }
            ReminderState.MISSED ->{
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification_missed_outlined),
                    contentDescription = null,
                )
            }
            ReminderState.STOPPED ->{
                TODO("reaction to the stopped state")
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun MedicineReminderItemPreview() {
    MedicineReminderTheme {
        MedicineReminderItem(
            state = ReminderState.UNSPECIFIED,
            medicineName = "Vitamin D",
            time = "10:15 AM",
            conflictsNumber = 4,
            subtitle = "2 Pills",
            image = null,
            defaultImage = R.drawable.pharmacy,
            onClick = {}
        )
    }
}
@Preview(showBackground = true)
@Composable
fun TakenMedicineReminderItemPreview() {
    MedicineReminderTheme {
        MedicineReminderItem(
            state = ReminderState.TAKEN,
            medicineName = "Vitamin D",
            time = "10:15 AM",
            conflictsNumber = 4,
            subtitle = "2 Pills",
            image = null,
            defaultImage = R.drawable.pharmacy,
            onClick = {}
        )
    }
}
@Preview(showBackground = true)
@Composable
fun MissedMedicineReminderItemPreview() {
    MedicineReminderTheme {
        MedicineReminderItem(
            state = ReminderState.MISSED,
            medicineName = "Vitamin D",
            time = "10:15 AM",
            conflictsNumber = 4,
            subtitle = "2 Pills",
            image = null,
            defaultImage = R.drawable.pharmacy,
            onClick = {}
        )
    }
}
@Preview(showBackground = true)
@Composable
fun UpcomingMedicineReminderItemPreview() {
    MedicineReminderTheme {
        MedicineReminderItem(
            state = ReminderState.UPCOMING,
            medicineName = "Vitamin D",
            time = "10:15 AM",
            conflictsNumber = 4,
            subtitle = "2 Pills",
            image = null,
            defaultImage = R.drawable.pharmacy,
            onClick = {}
        )
    }
}
@Preview(showBackground = true)
@Composable
fun StoppedMedicineReminderItemPreview() {
    MedicineReminderTheme {
        MedicineReminderItem(
            state = ReminderState.STOPPED,
            medicineName = "Vitamin D",
            time = "10:15 AM",
            conflictsNumber = 4,
            subtitle = "2 Pills",
            image = null,
            defaultImage = R.drawable.pharmacy,
            onClick = {}
        )
    }
}