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
    var rentLiveData = MutableLiveData<Resource<ResponseBody>>()
    var lstUserLiveData = MutableLiveData<Resource<ResponseUser>>()
    var deleteUserLiveData = MutableLiveData<Resource<BaseModel>>()

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

    fun getListUser(){
        viewModelScope.launch(IO){
            lstUserLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = mainRepository.getListUser()
                    if (response.isSuccessful) {
                        lstUserLiveData.postValue(Resource.success(response.body()))
                    } else {
                        lstUserLiveData.postValue(Resource.error("", null))
                    }
                }catch (e : Exception){
                    lstUserLiveData.postValue(Resource.error(e.message?:"", null))
                }
            } else {
                lstUserLiveData.postValue(Resource.error("No internet connection", null))
            }
        }
    }

    fun deleteUser(userModel : UserModel){
        viewModelScope.launch(IO){
            lstUserLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = mainRepository.deleteUser(userModel)
                    if (response.isSuccessful) {
                        deleteUserLiveData.postValue(Resource.success(response.body()))
                    } else {
                        deleteUserLiveData.postValue(Resource.error("", null))
                    }
                }catch (e : Exception){
                    deleteUserLiveData.postValue(Resource.error(e.message?:"", null))
                }
            } else {
                deleteUserLiveData.postValue(Resource.error("No internet connection", null))
            }
        }
    }
}