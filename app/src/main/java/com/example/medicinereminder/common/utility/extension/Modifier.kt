package com.example.medicinereminder.common.utility.extension

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer


@Composable
fun Modifier.fade(enable: Boolean): Modifier{
    val alpha by animateFloatAsState(targetValue = if(enable) 1f else 0f, label = "")
    return this then Modifier.graphicsLayer {
        this.alpha = alpha
    }
}