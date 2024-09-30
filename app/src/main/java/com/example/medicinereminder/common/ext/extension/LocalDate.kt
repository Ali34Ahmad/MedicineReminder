package com.example.medicinereminder.common.ext.extension

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
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