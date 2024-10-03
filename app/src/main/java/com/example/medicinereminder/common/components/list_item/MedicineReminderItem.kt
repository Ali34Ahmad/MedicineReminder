package com.example.medicinereminder.common.components.list_item

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.texts.FirstAndSecondSubText
import com.example.medicinereminder.common.components.texts.TitleAndTime
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun MedicineReminderItem(
    modifier: Modifier = Modifier,
    image: Int? = null,
    medicineName: String,
    subText:String,
    time: String,
    numberOfConflicts: Int? = null,
    @DrawableRes icon: Int? = null,
    @DrawableRes defaultImage: Int = R.drawable.pharmacy,
    reminderState:ReminderState,
    ) {
        val isDefault = image==null
        val medicineImage = image?: defaultImage
        Row(
            modifier= modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            //MedicineReminderImage(isDefault = isDefault, image = painterResource(id = medicineImage))
            Spacer(modifier = Modifier.width(16.dp))
            Column{
                TitleAndTime(title = medicineName, time = time)
                FirstAndSecondSubText(
                    modifier = modifier.weight(1f),
                    firstText = subText,
                    trailingIcon = icon,
                    secondText = "$numberOfConflicts conflicts",
                    reminderState = reminderState,
                )
            }
        }
    }


@Preview( showBackground = true)
@Composable
fun MedicineReminderItemPreview() {
    MaterialTheme {
        MedicineReminderItem(
            subText = "1 Pill",
            medicineName = "Vitamin D",
            time = "9:15 AM",
            numberOfConflicts = 3,
            reminderState = ReminderState.UPCOMING
        )
    }
}
@Preview( showBackground = true)
@Composable
fun MedicineReminderItemPreview2() {
    MedicineReminderTheme {
        MedicineReminderItem(
            subText = "1 Pill",
            medicineName = "Vitamin D",
            time = "9:15 AM",
            icon = Icons.Outlined.NotificationMissed,
            reminderState = ReminderState.MISSED,
        )
    }
}