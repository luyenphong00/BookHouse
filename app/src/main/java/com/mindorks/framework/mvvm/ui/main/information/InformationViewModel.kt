package com.mindorks.framework.mvvm.ui.main.information

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindorks.framework.mvvm.data.model.DetailUserModel
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class InformationViewModel(private val mainRepository: MainRepository,
                           private val networkHelper: NetworkHelper
) : ViewModel() {

    var userModel = MutableLiveData<Resource<DetailUserModel>>()
    var updateUserModel = MutableLiveData<Resource<String>>()

    fun getDetailAcc(id : String){
        viewModelScope.launch(IO){
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getDetailUser(id).let {
                    if (it.isSuccessful) {
                        userModel.postValue(Resource.success(it.body()))
                    } else userModel.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else userModel.postValue(Resource.error("No internet connection", null))
        }
    }

    fun updateUser(userModel: DetailUserModel){
        viewModelScope.launch(IO){

        }
    }

}