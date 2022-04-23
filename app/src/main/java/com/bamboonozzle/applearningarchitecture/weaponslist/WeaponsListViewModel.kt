package com.bamboonozzle.applearningarchitecture.weaponslist

import androidx.lifecycle.*
import com.bamboonozzle.applearningarchitecture.Event
import com.bamboonozzle.applearningarchitecture.data.Result
import com.bamboonozzle.applearningarchitecture.data.Weapon
import com.bamboonozzle.applearningarchitecture.data.source.WeaponRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class WeaponsListViewModel(
    private val weaponRepository: WeaponRepository
) : ViewModel() {

    private val _refresh = MutableLiveData<Boolean>()

    private val _weaponsList : LiveData<List<Weapon>> = _refresh.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            val res = weaponRepository.getWeapons()
            if (res is Result.Success) {
                emit(res.data)
            } else if (res is Result.Error){
                _errorLoadToast.postValue(Event(res.exception.message ?: ""))
            }
        }
    }

    val weaponsList: LiveData<List<Weapon>> = _weaponsList

    private val _weaponDetailEvent: MutableLiveData<Event<String>> = MutableLiveData()
    val weaponDetailEvent : LiveData<Event<String>> = _weaponDetailEvent

    private val _errorLoadToast: MutableLiveData<Event<String>> = MutableLiveData()
    val errorLoadToast: LiveData<Event<String>> = _errorLoadToast

    fun loadWeapons() {
        _refresh.value = true
    }

    fun onWeaponClicked(uuid: String) {
        _weaponDetailEvent.value = Event(uuid)
    }

}