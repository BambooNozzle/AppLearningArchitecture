package com.bamboonozzle.applearningarchitecture.data

import com.google.gson.annotations.SerializedName

data class WeaponsData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: List<Weapon>
)

data class WeaponData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: Weapon
)

data class Weapon(
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("displayIcon")
    val displayIcon: String,
    @SerializedName("weaponStats")
    val weaponStats: WeaponStats,
    @SerializedName("shopData")
    val shopData: ShopData
) {
}

data class WeaponStats(
    @SerializedName("magazineSize")
    val magazineSize: Int
) {
    fun getMagazineSizeString() = magazineSize.toString()
}

data class ShopData(
    @SerializedName("cost")
    val cost: Int
) {
    fun getCostString() = cost.toString()
}
