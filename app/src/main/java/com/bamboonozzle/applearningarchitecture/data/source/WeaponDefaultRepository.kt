package com.bamboonozzle.applearningarchitecture.data.source

import com.bamboonozzle.applearningarchitecture.data.Weapon
import com.bamboonozzle.applearningarchitecture.data.Result

class WeaponDefaultRepository(
    private val remoteDataSource: WeaponDataSource
): WeaponRepository {
    override suspend fun getWeapons(): Result<List<Weapon>> = remoteDataSource.getWeapons()

    override suspend fun getWeaponByUuid(uuid: String): Result<Weapon> = remoteDataSource.getWeaponByUuid(uuid)
}