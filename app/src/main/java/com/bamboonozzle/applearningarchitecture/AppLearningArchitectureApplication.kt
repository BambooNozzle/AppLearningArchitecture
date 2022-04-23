package com.bamboonozzle.applearningarchitecture

import android.app.Application
import com.bamboonozzle.applearningarchitecture.data.ServiceLocator
import com.bamboonozzle.applearningarchitecture.data.source.WeaponRepository

class AppLearningArchitectureApplication : Application() {
    val weaponRepository: WeaponRepository
        get() = ServiceLocator.getWeaponRepository()
}