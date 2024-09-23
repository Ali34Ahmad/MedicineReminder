package com.example.medicinereminder.data.model


data class Address(
//    @field:@SerializedName
    val stateOrGovernorate: String,
    val city: String,
    val street: String?,
    val buildingNumber: String?,
    val apartmentNumber: String?,
)