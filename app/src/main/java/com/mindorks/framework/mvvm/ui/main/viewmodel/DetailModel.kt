package com.mindorks.framework.mvvm.ui.main.viewmodel

import androidx.lifecycle.*
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.data.model.EquipmentsResponse
import com.mindorks.framework.mvvm.data.model.User
import com.mindorks.framework.mvvm.data.model.UserModel
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DetailModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    var lstEquipment = MutableLiveData<Resource<EquipmentsResponse>>()
    var lstService = MutableLiveData<Resource<EquipmentsResponse>>()
    fun fetchEquipments(){
        viewModelScope.launch(IO){
            lstEquipment.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = mainRepository.fetchEquipments()
                    if (response.isSuccessful) {
                        lstEquipment.postValue(Resource.success(response.body()))
                    } else {
                        lstEquipment.postValue(Resource.error(response.errorBody().toString(), null))
                    }
                }catch (e : Exception){
                    lstEquipment.postValue(Resource.error(e.message?:"", null))
                }
            } else {
                lstEquipment.postValue(Resource.error("No internet connection", null))
            }
        }
    }

    fun fetchService(){
        viewModelScope.launch(IO){
            lstService.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = mainRepository.services()
                    if (response.isSuccessful) {
                        lstService.postValue(Resource.success(response.body()))
                    } else {
                        lstService.postValue(Resource.error(response.errorBody().toString(), null))
                    }
                }catch (e : Exception){
                    lstService.postValue(Resource.error(e.message?:"", null))
                }
            } else {
                lstService.postValue(Resource.error("No internet connection", null))
            }
        }
    }

    fun rentals(){
        viewModelScope.launch(IO){
            lstService.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = mainRepository.services()
                    if (response.isSuccessful) {
                        lstService.postValue(Resource.success(response.body()))
                    } else {
                        lstService.postValue(Resource.error(response.errorBody().toString(), null))
                    }
                }catch (e : Exception){
                    lstService.postValue(Resource.error(e.message?:"", null))
                }
            } else {
                lstService.postValue(Resource.error("No internet connection", null))
            }
        }
    }
}