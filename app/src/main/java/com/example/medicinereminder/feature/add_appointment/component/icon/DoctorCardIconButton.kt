package com.example.medicinereminder.feature.add_appointment.component.icon

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun DoctorCardIcon(
    isToggled: Boolean,
    modifier: Modifier = Modifier,
) {
    val angle by animateFloatAsState(
        animationSpec = tween(400),
        targetValue =if(isToggled) 0f else 180f, label = ""
    )

        Icon(
            modifier = modifier
                .padding(end = MaterialTheme.spacing.medium16)
                .size(MaterialTheme.sizing.medium24)
                .graphicsLayer {
                    rotationZ = angle
                    scaleY = 1.2f
                    scaleX = 1.2f
                },
            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_expand_less_24),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )

}