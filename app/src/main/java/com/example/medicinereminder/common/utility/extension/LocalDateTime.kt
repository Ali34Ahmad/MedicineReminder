package com.example.medicinereminder.common.utility.extension

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


fun LocalDateTime.toLong(): Long {
    val zoneId: ZoneId = ZoneId.systemDefault() // Or yourdesired ZoneId
    return this.atZone(zoneId).toInstant().toEpochMilli()
}

fun longToLocalDateTime(milliseconds: Long): LocalDateTime {
    val zoneId: ZoneId = ZoneId.systemDefault() // Or your desired ZoneId
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), zoneId)
}
val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
fun LocalDateTime.toFormattedString(): String {
    return this.format(formatter)
}
fun Long.toFormattedString(): String {
    return longToLocalDateTime(this).toFormattedString()
}