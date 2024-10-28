package com.example.medicinereminder.feature.home_screen.presentation.home_main

import com.example.medicinereminder.data.model.TimedEvent

sealed interface HomeAction {
    data class UpdateTeb(val tab: HomeTab): HomeAction
    data class OpenBottomSheet(val bottomSheet: HomeBottomSheet, val reminder: TimedEvent): HomeAction
    data object CloseBottomSheet: HomeAction
}