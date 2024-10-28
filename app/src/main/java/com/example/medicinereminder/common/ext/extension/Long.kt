package com.example.medicinereminder.common.ext.extension

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun Long.toLocalDateTime(): LocalDateTime{
    val zoneId: ZoneId = ZoneId.systemDefault() // Or your desired ZoneId
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), zoneId)
}