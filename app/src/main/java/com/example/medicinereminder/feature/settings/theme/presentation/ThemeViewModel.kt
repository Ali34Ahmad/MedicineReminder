package com.example.medicinereminder.feature.settings.theme.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(

): ViewModel(){
    //here need for data store to get the theme settings
    private val _uiState = mutableStateOf(ThemeUIState())
    val uiState: State<ThemeUIState> get() =  _uiState

    fun onAction(action: ThemeAction){
        when(action){
            ThemeAction.ToggleAutoMode -> {
                _uiState.value = _uiState.value.copy(isAutoModeActivated = !_uiState.value.isAutoModeActivated)
            }
            ThemeAction.ToggleNightMode -> {
                _uiState.value = _uiState.value.copy(isNightModeActivated = !_uiState.value.isNightModeActivated)
            }
            ThemeAction.ToggleDynamicTheme ->{
                _uiState.value = _uiState.value.copy(isDynamicThemeActivated = !_uiState.value.isDynamicThemeActivated)
            }
            ThemeAction.CustomizeTheme -> TODO("not yet implemented")
            ThemeAction.ResetDefaultColorsSettings -> {
                _uiState.value = _uiState.value.copy(

                    isDynamicThemeActivated = false,
                    isAutoModeActivated = false,
                    isNightModeActivated = false,
                    )
            }
            is ThemeAction.ChangeFontSize -> {
                _uiState.value = _uiState.value.copy(fontSize = action.newSize)
            }
            ThemeAction.ResetFontsSettings -> {
                _uiState.value = _uiState.value.copy(fontSize = DEFAULT_FONT_SIZE)
            }
            ThemeAction.NavigateUp -> TODO("need navigation")
        }
    }
}