package com.example.medicinereminder.common.utility.extension

import com.example.medicinereminder.data.local.entity.Doctor

fun Doctor.doesMatchSearchQuery(query: String): Boolean{
    val matchingCombinations = listOf(
        this.name,
        this.specialty
    )

    return matchingCombinations.any{
        it.contains(query, ignoreCase = true)
    }
}