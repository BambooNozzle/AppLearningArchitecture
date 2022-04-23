package com.bamboonozzle.applearningarchitecture

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bamboonozzle.applearningarchitecture.data.source.WeaponRepository
import com.bamboonozzle.applearningarchitecture.weapondetail.WeaponDetailViewModel
import com.bamboonozzle.applearningarchitecture.weaponslist.WeaponsListFragment
import com.bamboonozzle.applearningarchitecture.weaponslist.WeaponsListViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val weaponRepository: WeaponRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(WeaponsListViewModel::class.java) -> WeaponsListViewModel(weaponRepository)
            isAssignableFrom(WeaponDetailViewModel::class.java) -> WeaponDetailViewModel(weaponRepository)
            else -> IllegalArgumentException("invalid")
        }
    } as T
}