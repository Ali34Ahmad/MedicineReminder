package com.example.medicinereminder.common.components.list_item

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.texts.FirstAndSecondSubText
import com.example.medicinereminder.common.components.texts.TitleAndTime
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun AppointmentItem(
    modifier: Modifier = Modifier,
    image: Int? = null,
    doctorName: String,
    speciality: String,
    time: String,
    @DrawableRes icon: Int? = null,
    @DrawableRes defaultImage: Int = R.drawable.doctor_default_image,
    //reminderState: ReminderState,
) {
    val doctorImage = image ?: defaultImage
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = doctorImage),
            contentDescription = null,
            modifier = Modifier
                .size(MaterialTheme.sizing.large52)
                .clip(shape = CircleShape)
        )
        Column(modifier = Modifier.padding(start = MaterialTheme.spacing.medium16)) {
            TitleAndTime(
                title = doctorName,
                time = time,
            )
            FirstAndSecondSubText(
                trailingIcon = icon,
                firstText = speciality,
                reminderState = ReminderState.UPCOMING,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AppointmentItemPreview() {
    MedicineReminderTheme {
        AppointmentItem(
            doctorName = "Ali Mansoura",
            speciality = "Ophthalmologist",
            time = "9:00 AM",
        )
    }
}
@Preview(showBackground = true)
@Composable
fun AppointmentItemWithIconPreview() {
    MedicineReminderTheme {
        AppointmentItem(
            doctorName = "Ali Mansoura",
            speciality = "Ophthalmologist",
            time = "9:00 AM",
            icon = R.drawable.ic_clock_outlined
        )
    }
}