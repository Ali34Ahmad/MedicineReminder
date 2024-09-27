package com.example.medicinereminder.core.components.list_items

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.core.components.images.MedicineReminderImage
import com.example.medicinereminder.core.components.texts.TitleDesc
import com.example.medicinereminder.core.components.texts.TrailingTimeItem
import com.example.medicinereminder.ui.theme.MedicineReminderTheme

@Composable
fun AppointmentItem(
    modifier: Modifier = Modifier,
    image: Int? = null,
    doctorName:String,
    speciality:String,
    time:String,
    @DrawableRes icon: Int? = null,
    @DrawableRes defaultImage: Int = R.drawable.doctor
) {
    val isDefault = image==null
    val doctorImage = image?:defaultImage
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        MedicineReminderImage(
            isDefault = isDefault,
            image = painterResource(id = doctorImage)
        )
        Spacer(modifier = Modifier.width(16.dp))
        TitleDesc(title = doctorName, desc = speciality)
        TrailingTimeItem(
            modifier = modifier.weight(1f),
            time = time,
            trailingIcon = icon,
        )
    }

}

@Preview(showBackground = true)
@Composable
fun AppointmentItemPreview() {
    MedicineReminderTheme {
        AppointmentItem(
            doctorName = "Ali Mansoura",
            speciality = "Ophthalmologist",
            time = "19 Dec"
        )
    }
}