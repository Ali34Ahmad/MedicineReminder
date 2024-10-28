package com.example.medicinereminder.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 0.dp,
    val small1: Dp = 1.dp,
    val small2: Dp = 2.dp,
    val small4: Dp = 4.dp,
    val small6: Dp = 6.dp,
    val small8: Dp = 8.dp,
    val medium12: Dp = 12.dp,
    val medium16: Dp = 16.dp,
    val medium18: Dp = 18.dp,
    val medium24: Dp = 24.dp,
    val medium28: Dp = 28.dp,
    val large32: Dp = 32.dp,
    val large48: Dp = 48.dp,
    val large52: Dp = 52.dp,
    val large64: Dp = 64.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable @ReadOnlyComposable get() =
        LocalSpacing.current
