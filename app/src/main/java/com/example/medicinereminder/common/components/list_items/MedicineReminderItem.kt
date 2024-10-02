package com.example.medicinereminder.common.components.list_items

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
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
import com.example.medicinereminder.common.components.images.MedicineReminderImage
import com.example.medicinereminder.common.components.texts.TitleDesc
import com.example.medicinereminder.common.components.texts.TrailingTimeItem
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun MedicineReminderItem(
    modifier: Modifier = Modifier,
    image: Int? = null,
    medicineName: String,
    subtitle:String,
    time: String,
    conflictsMsg: String? = null,
    @DrawableRes icon: Int? = null,
    @DrawableRes defaultImage: Int = R.drawable.pharmacy,
    ) {
        val isDefault = image==null
        val medicineImage = image?: defaultImage
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            MedicineReminderImage(isDefault = isDefault, image = medicineImage)
            Spacer(modifier = Modifier.width(16.dp))
            TitleDesc(title = medicineName, subtitle = subtitle)
            TrailingTimeItem(
                modifier = modifier.weight(1f),
                time = time,
                trailingIcon = icon,
                desc = conflictsMsg
            )
        }
    }


@Preview( showBackground = true)
@Composable
fun MedicineReminderItemPreview() {
    MaterialTheme {
        MedicineReminderItem(
            subtitle = "1 Pill",
            medicineName = "Vitamin D",
            time = "9:15 AM",
            conflictsMsg = "3 conflicts",
        )
    }
}
@Preview( showBackground = true)
@Composable
fun MedicineReminderItemPreview2() {
    MedicineReminderTheme {
        MedicineReminderItem(
            subtitle = "1 Pill",
            medicineName = "Vitamin D",
            time = "9:15 AM",
            icon = R.drawable.ic_launcher_foreground,
        )
    }
}