package com.bamboonozzle.applearningarchitecture.weapondetail

import androidx.lifecycle.*
import com.bamboonozzle.applearningarchitecture.Event
import com.bamboonozzle.applearningarchitecture.data.Result
import com.bamboonozzle.applearningarchitecture.data.Weapon
import com.bamboonozzle.applearningarchitecture.data.source.WeaponRepository
import kotlinx.coroutines.Dispatchers

class WeaponDetailViewModel(weaponRepository: WeaponRepository) : ViewModel() {
    fun loadWeaponDetail(uuid: String) {
        _isLoading.value = true
        _uuid.value = uuid
    }

    private val _uuid = MutableLiveData<String>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorEvent = MutableLiveData<Event<String>>()
    val errorEvent: LiveData<Event<String>> = _errorEvent

    private val _weapon: LiveData<Weapon> = _uuid.switchMap { uuid ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val res = weaponRepository.getWeaponByUuid(uuid)
            if (res is Result.Success) {
                emit(res.data)
            } else if (res is Result.Error){
                _errorEvent.postValue(Event(res.exception.message ?: ""))
            }
            _isLoading.postValue(false)
        }
    }

    val weapon: LiveData<Weapon> = _weapon

}