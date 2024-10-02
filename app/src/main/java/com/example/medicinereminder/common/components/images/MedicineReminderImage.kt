package com.example.medicinereminder.common.components.images

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme


@Composable
fun MedicineReminderImage(
    modifier: Modifier = Modifier,
    isDefault: Boolean,
    @DrawableRes image: Int,
) {
    val boxSize = 52.dp
    val fraction = if(isDefault) 0.75f else 1f
    Box(
        modifier = modifier
            .size(boxSize)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
        ,
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(fraction)
                ,
            contentScale = ContentScale.Fit
        )
    }
}

@Preview
@Composable
fun MedicineReminderImagePreview() {
    MedicineReminderTheme{
        MedicineReminderImage(
            isDefault = true,
            image = R.drawable.pharmacy,

        )

    }
}