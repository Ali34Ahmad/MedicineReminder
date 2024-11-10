package com.example.medicinereminder.feature.settings.theme.presentation

const val DEFAULT_FONT_SIZE = 2
data class ThemeUIState(
    val isNightModeActivated: Boolean = false,
    val isAutoModeActivated: Boolean = false,
    val isDynamicThemeActivated: Boolean = false,
    val fontSize: Int = DEFAULT_FONT_SIZE
)