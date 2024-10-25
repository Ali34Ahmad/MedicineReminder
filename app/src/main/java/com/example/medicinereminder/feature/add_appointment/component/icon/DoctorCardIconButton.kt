package com.example.medicinereminder.feature.add_appointment.component.icon

import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun BoxScope.DoctorCardIconButton(
    isToggled: Boolean,
    onClick:() -> Unit,
    modifier: Modifier = Modifier,
) {
    val angle by animateFloatAsState(
        animationSpec = tween(400),
        targetValue =if(isToggled) 0f else 180f, label = ""
    )
    IconButton(
        onClick = onClick,
        modifier = modifier
            .align(Alignment.TopEnd)
            .padding(
                end = MaterialTheme.spacing.medium16,
                top = 72.dp
            ),
    ) {
        Icon(
            modifier = Modifier.size(MaterialTheme.sizing.medium24)
                .graphicsLayer {
                    rotationZ = angle
                },
            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_expand_less_24),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}