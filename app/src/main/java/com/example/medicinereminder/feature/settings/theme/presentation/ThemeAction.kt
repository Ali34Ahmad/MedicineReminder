package com.example.medicinereminder.feature.settings.theme.presentation

sealed interface ThemeAction {
    //Top bar actions
    data object NavigateUp: ThemeAction

    //Settings actions
    //Colors settings
    data object ToggleNightMode: ThemeAction
    data object ToggleAutoMode: ThemeAction
    data object ToggleDynamicTheme: ThemeAction
    data object CustomizeTheme: ThemeAction
    data object ResetDefaultColorsSettings: ThemeAction
    //Fonts Settings
    data class ChangeFontSize(val newSize: Int): ThemeAction
    data object ResetFontsSettings: ThemeAction
}