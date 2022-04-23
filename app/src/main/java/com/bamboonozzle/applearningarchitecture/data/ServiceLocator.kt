package com.bamboonozzle.applearningarchitecture.data

import com.bamboonozzle.applearningarchitecture.data.source.WeaponDataSource
import com.bamboonozzle.applearningarchitecture.data.source.WeaponDefaultRepository
import com.bamboonozzle.applearningarchitecture.data.source.WeaponRepository
import com.bamboonozzle.applearningarchitecture.data.source.remote.ApiService
import com.bamboonozzle.applearningarchitecture.data.source.remote.WeaponRemoteDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {

    private var weaponRepository: WeaponRepository? = null
    private var retrofit: Retrofit? = null
    private var apiService: ApiService? = null

    private fun getRetrofit(): Retrofit = retrofit ?: createRetrofit()

    private fun createRetrofit(): Retrofit {
        val rtrft = Retrofit.Builder()
            .baseUrl("https://valorant-api.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit = rtrft
        return rtrft
    }

    private fun getApiService() = apiService ?: createApiService()

    private fun createApiService(): ApiService {
        val apiService = getRetrofit().create(ApiService::class.java)
        this.apiService = apiService
        return apiService
    }

    fun getWeaponRepository(): WeaponRepository = weaponRepository ?: createWeaponRepository()

    private fun createWeaponRepository(): WeaponRepository {
        val weaponRepository = WeaponDefaultRepository(createWeaponDataSource())
        this.weaponRepository = weaponRepository
        return weaponRepository
    }

    private fun createWeaponDataSource(): WeaponDataSource = WeaponRemoteDataSource(getApiService())

}