package com.mindorks.framework.mvvm.ui.main.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mindorks.framework.mvvm.common.BaseViewModel
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

    fun getRentalsToMettingRoom(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            lstHistory.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.rentalsGetMettingRoom(id).let {
                    if (it.isSuccessful){
                        lstHistory.postValue(Resource.success(it.body()))
                    }else {
                        lstHistory.postValue(Resource.error("",null))
                    }
                }
            }else {
                lstHistory.postValue(Resource.error("",null))
            }
        }
    }
}