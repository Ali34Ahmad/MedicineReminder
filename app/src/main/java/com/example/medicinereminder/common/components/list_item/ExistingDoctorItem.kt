package com.example.medicinereminder.common.components.list_item

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.texts.TitleAndSubtitle
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun ExistingDoctorItem(
    modifier: Modifier = Modifier,
    doctor: Doctor,
    onClick:(Doctor) -> Unit,
    onButtonClick: (Doctor) -> Unit,
    @DrawableRes defaultImage: Int
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                MaterialTheme.spacing.medium16,
            )
            .clickable {
                onClick(doctor)
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
            Image(
                painter = painterResource(id = defaultImage),
                contentDescription = null,
                modifier = Modifier
                    .size(
                        MaterialTheme.sizing.large44
                    )
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium16))
            TitleAndSubtitle(
                title = doctor.name,
                subtitle = doctor.specialty,
                isWarning = false,
                modifier = Modifier.weight(1f)
            )

        IconButton(onClick = {
            onButtonClick(doctor)
        }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right_up) ,
                contentDescription = null
            )

    }
    }
}

@Preview(showBackground = true)
@Composable
fun ExistingDoctorItemPreview() {
    MedicineReminderTheme {
        ExistingDoctorItem(
            doctor = Doctor(
                name = "Ali",
                specialty = "Eyes",
                imageFileName = "",
            ),
            onClick = {  },
            onButtonClick = {  },
            defaultImage = R.drawable.doctor_image
        )
    }
}