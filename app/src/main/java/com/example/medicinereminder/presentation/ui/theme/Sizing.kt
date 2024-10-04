package com.example.medicinereminder.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Sizing(
    val default: Dp = 0.dp,
    val small6:Dp=6.dp,
    val small16:Dp=16.dp,
    val large32:Dp=32.dp,
    val large44:Dp=44.dp,
    val large52:Dp=52.dp,
)

val LocalSizing = compositionLocalOf { Sizing() }

val MaterialTheme.sizing: Sizing
    @Composable @ReadOnlyComposable get() =
        LocalSizing.current
