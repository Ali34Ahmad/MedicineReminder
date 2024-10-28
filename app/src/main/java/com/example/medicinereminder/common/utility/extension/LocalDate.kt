package com.example.medicinereminder.common.utility.extension

import java.time.LocalDate


fun listFromRange(
    startDate: LocalDate,
    endDate: LocalDate
): List<LocalDate>{
    val days = mutableListOf<LocalDate>()
    var currentDate = startDate
    while (!currentDate.isAfter(endDate)) {
        days.add(currentDate)
        currentDate = currentDate.plusDays(1) // Move to the next day
    }
    return days.toList()
}