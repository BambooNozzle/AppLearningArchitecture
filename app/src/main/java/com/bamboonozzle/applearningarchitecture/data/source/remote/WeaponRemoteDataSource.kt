package com.bamboonozzle.applearningarchitecture.data.source.remote

import com.bamboonozzle.applearningarchitecture.data.Weapon
import com.bamboonozzle.applearningarchitecture.data.source.WeaponDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.bamboonozzle.applearningarchitecture.data.Result

class WeaponRemoteDataSource(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): WeaponDataSource {

    override suspend fun getWeapons(): Result<List<Weapon>> = withContext(ioDispatcher) {
        try {
            val res = apiService.getWeapons()
            return@withContext Result.Success(res.data)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun getWeaponByUuid(uuid: String): Result<Weapon> = withContext(ioDispatcher) {
        try {
            val res = apiService.getWeaponByUuid(uuid)
            return@withContext Result.Success(res.data)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }
}