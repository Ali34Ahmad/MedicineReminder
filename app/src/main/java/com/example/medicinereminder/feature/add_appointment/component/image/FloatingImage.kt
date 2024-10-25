package com.example.medicinereminder.feature.add_appointment.component.image

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.theme.sizing

@Composable
fun BoxScope.FloatingImage(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int = R.drawable.doctor_image,
) {
    Image(
        modifier = modifier
            .align(Alignment.TopCenter)
            .size(
                MaterialTheme.sizing.large72
            )
            .graphicsLayer {
                clip = true
                shape = CircleShape
            }
        ,
        painter = painterResource(id = image),
        contentDescription = null,
    )
}