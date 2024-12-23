package com.example.medicinereminder.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Sizing(
    val default: Dp = 0.dp,
    val small1:Dp=1.dp,
    val small2: Dp=2.dp,
    val small6:Dp=6.dp,
    val small8: Dp = 8.dp,
    val small16:Dp=16.dp,
    val medium24:Dp = 24.dp,
    val large32:Dp=32.dp,
    val large36:Dp=36.dp,
    val large44:Dp=44.dp,
    val large52:Dp=52.dp,
    val large56:Dp=56.dp,
    val large132:Dp=132.dp,
    val large62:Dp=62.dp,
    val large64:Dp=64.dp,
    val large72:Dp=72.dp,
    val large76:Dp=76.dp,
    val extraLarge:Dp= 360.dp
)

val LocalSizing = compositionLocalOf { Sizing() }

val MaterialTheme.sizing: Sizing
    @Composable @ReadOnlyComposable get() =
        LocalSizing.current
