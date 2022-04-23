package com.bamboonozzle.applearningarchitecture.data.source.remote

import com.bamboonozzle.applearningarchitecture.data.Weapon
import com.bamboonozzle.applearningarchitecture.data.WeaponData
import com.bamboonozzle.applearningarchitecture.data.WeaponsData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("weapons")
    suspend fun getWeapons(): WeaponsData

    @GET("weapons/{weaponUuid}")
    suspend fun getWeaponByUuid(@Path("weaponUuid") uuid: String): WeaponData

}