package com.mindorks.framework.mvvm.ui.main.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.data.model.BaseModel
import com.mindorks.framework.mvvm.data.model.EntalsGetMettingModel
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    var lstHistory = MutableLiveData<Resource<EntalsGetMettingModel>>()
    var deleteResponse  = MutableLiveData<Resource<BaseModel>>()

    fun getRentalsToMettingRoom(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            lstHistory.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.rentalsGetMettingRoom(id).let {
                    if (it.isSuccessful){
                        lstHistory.postValue(Resource.success(it.body()))
                    }else {
                        lstHistory.postValue(Resource.error("Không lấy được thông tin lịch sử",null))
                    }
                }
            }else {
                lstHistory.postValue(Resource.error("Mất kết nối mạng",null))
            }
        }
    }

    fun deleteRentals(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.deleteRentals(id).let {
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
}