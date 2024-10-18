package com.example.medicinereminder.common.ext.extension

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDateTime.toLong(): Long {
    val zoneId: ZoneId = ZoneId.systemDefault()
    return this.atZone(zoneId).toInstant().toEpochMilli()
}


fun LocalDateTime.toTimeFormattedString(): String {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a",Locale.getDefault())
    return this.format(formatter)
}

fun LocalDateTime.toDateFormattedString(): String{
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy",Locale.getDefault())
    return this.format(formatter)
}