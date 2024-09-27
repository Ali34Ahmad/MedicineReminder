package com.example.medicinereminder.core.utility.extension

fun Int.toOrdered(): String{
    val suffix = this.getOrderedSuffix()
    return "$this$suffix"
}
fun Int.getOrderedSuffix(): String{
    if(this in 11..13)
        return "th"
    val lastDigit = this.toString().last()
    return when(lastDigit){
        '1' -> "st"
        '2' -> "nd"
        '3' -> "rd"
        else -> "th"
    }
}