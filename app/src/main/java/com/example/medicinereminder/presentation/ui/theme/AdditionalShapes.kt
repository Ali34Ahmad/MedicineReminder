package com.example.medicinereminder.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AdditionalShapes(
    val default: Dp = 0.dp,
    val small8: Dp=8.dp,
    val medium20: Dp = 20.dp,
    val medium28: Dp = 28.dp,
)

val LocalAdditionalShapes = compositionLocalOf { AdditionalShapes() }

val MaterialTheme.additionalShapes: AdditionalShapes
    @Composable @ReadOnlyComposable get() =
        LocalAdditionalShapes.current