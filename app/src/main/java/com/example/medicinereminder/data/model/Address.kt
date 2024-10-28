package com.example.medicinereminder.data.model


data class Address(
//    @field:@SerializedName
    val stateOrGovernorate: String ="",
    val city: String = "",
    val street: String? = null,
    val buildingNumber: String? = null,
    val apartmentNumber: String? = null,
){
    override fun toString(): String {
        return "$stateOrGovernorate _ $city _ $street"
    }
}