package com.bamboonozzle.applearningarchitecture.data.source

import com.bamboonozzle.applearningarchitecture.data.Weapon
import com.bamboonozzle.applearningarchitecture.data.Result

interface WeaponDataSource {

    suspend fun getWeapons(): Result<List<Weapon>>

    suspend fun getWeaponByUuid(uuid: String): Result<Weapon>

}