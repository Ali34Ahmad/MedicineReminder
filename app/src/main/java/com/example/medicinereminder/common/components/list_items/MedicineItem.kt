package com.example.medicinereminder.common.components.list_items

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.images.MedicineReminderImage
import com.example.medicinereminder.common.components.texts.TitleDesc

@Composable
fun MedicineItem(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int?=null,
    medicineName: String,
    desc:String,
    @DrawableRes icon: Int,
    @DrawableRes defaultImage: Int = R.drawable.pharmacy,
    isWarning: Boolean = false
    ) {
    val isDefault = image==null
    val medicineImage = image?: defaultImage
    val color = if (isWarning)
        MaterialTheme.colorScheme.error
    else
        MaterialTheme.colorScheme.onSurfaceVariant

    Row(
        modifier= modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        MedicineReminderImage(isDefault = isDefault, image = medicineImage)
        Spacer(modifier = Modifier.width(16.dp))
        TitleDesc(title = medicineName, subtitle = desc, isWarning = isWarning)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(36.dp),
            tint = color
        )
    }
}
@Preview( showBackground = true)
@Composable
fun MedicineItemPreview() {
    MaterialTheme {
        MedicineItem(
            desc = "1 Pill",
            medicineName = "Vitamin D",
            icon = R.drawable.ic_launcher_foreground
        )
    }
}
@Preview( showBackground = true)
@Composable
fun MedicineItemWarningPreview() {
    MaterialTheme {
        MedicineItem(
            desc = "Out of stock",
            medicineName = "Vitamin D",
            icon = R.drawable.ic_launcher_foreground,
            isWarning = true
        )
    }
}