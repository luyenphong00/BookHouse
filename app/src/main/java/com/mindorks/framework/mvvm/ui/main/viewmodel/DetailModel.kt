package com.mindorks.framework.mvvm.ui.main.viewmodel

import androidx.lifecycle.*
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.data.model.*
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class DetailModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    var lstEquipment = MutableLiveData<Resource<EquipmentsResponse>>()
    var lstService = MutableLiveData<Resource<EquipmentsResponse>>()
    var lstUser = MutableLiveData<Resource<ResponseUser>>()
    var rentLiveData = MutableLiveData<Resource<ResponseBody>>()

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

    fun rent(roomBock: RoomBock){
        viewModelScope.launch(IO){
            rentLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = mainRepository.rent(roomBock)
                    if (response.isSuccessful) {
                        rentLiveData.postValue(Resource.success(null))
                    } else {
                        rentLiveData.postValue(Resource.error("", null))
                    }
                }catch (e : Exception){
                    rentLiveData.postValue(Resource.error(e.message?:"", null))
                }
            } else {
                rentLiveData.postValue(Resource.error("No internet connection", null))
            }
        }
    }

    fun getLstUser(){
        viewModelScope.launch(IO){
            rentLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = mainRepository.getListUser()
                    if (response.isSuccessful) {
                        lstUser.postValue(Resource.success(response.body()))
                    } else {
                        lstUser.postValue(Resource.error("", null))
                    }
                }catch (e : Exception){
                    lstUser.postValue(Resource.error(e.message?:"", null))
                }
            } else {
                lstUser.postValue(Resource.error("No internet connection", null))
            }
        }
    }
}