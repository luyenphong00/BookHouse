package com.mindorks.framework.mvvm.ui.main.manager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.data.model.BaseModel
import com.mindorks.framework.mvvm.data.model.DataResponseDepartment
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Create by LuyenPhong on 9/25/2022
 * phone 0358844343
 */
class ManagerViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    var deleteResponse  = MutableLiveData<Resource<BaseModel>>()
    var updateLiveData  = MutableLiveData<Resource<BaseModel>>()

    fun deleteMeeting(body : DataResponseDepartment){
        viewModelScope.launch(Dispatchers.IO) {
            deleteResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.deleteMeetingRooms(body).let {
                    if (it.isSuccessful){
                        deleteResponse.postValue(Resource.success(it.body()))
                    }else {
                        deleteResponse.postValue(Resource.error("Thất bại",null))
                    }
                }
            }else {
                deleteResponse.postValue(Resource.error("Thất bại",null))
            }
        }
    }

    fun updateMeetingRooms(body : DataResponseDepartment){
        viewModelScope.launch(Dispatchers.IO) {
            updateLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.updateMeetingRooms(body).let {
                    if (it.isSuccessful){
                        updateLiveData.postValue(Resource.success(it.body()))
                    }else {
                        updateLiveData.postValue(Resource.error("Thất bại",null))
                    }
                }
            }else {
                updateLiveData.postValue(Resource.error("Thất bại",null))
            }
        }
    }

}