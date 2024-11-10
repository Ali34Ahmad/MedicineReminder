package com.example.medicinereminder.presentation.ui.constants

import com.example.medicinereminder.R

object TabItems {
    fun homeScreen()= listOf(
        R.string.all,
        R.string.medicines,
        R.string.appointments,
        R.string.refill,
    )
    fun medicinesScreen() = listOf(
        R.string.active,
        R.string.running_low,
        R.string.stopped,
    )
    fun appointmentsScreen() = listOf(
        R.string.upcoming,
        R.string.completed,
        R.string.missed,
    )
    fun remindersScreen() = listOf(
        R.string.today,
        R.string.medicines,
        R.string.appointments,
        R.string.refill,
    )
    fun programScreen() = listOf(
        R.string.every_day,
        R.string.specific_days,
        R.string.intervals,
        R.string.cycle,
    )

}