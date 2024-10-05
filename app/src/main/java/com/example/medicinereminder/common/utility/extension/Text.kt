package com.example.medicinereminder.common.utility.extension

import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Calendar

//String
fun String.capitalizeFirstLetter(): String {
    if (this.isEmpty()) {
        return this
    }

    return this[0].uppercase() + this.substring(1).lowercase()
}

//DayOfWeek
fun DayOfWeek.toShortName(): String {
    return this.name.capitalizeFirstLetter().substring(0, 3)
}

//Long
fun Long?.toLocalDate():LocalDate {
    val calendar = Calendar.getInstance()
    if (this != null)
        calendar.timeInMillis = this
    val date = LocalDate.of(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH) + 1,
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    return date
}