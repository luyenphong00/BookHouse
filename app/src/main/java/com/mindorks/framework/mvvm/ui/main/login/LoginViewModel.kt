package com.mindorks.framework.mvvm.ui.main.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindorks.framework.mvvm.data.api.ApiHelper
import com.mindorks.framework.mvvm.data.model.DetailUserModel
import com.mindorks.framework.mvvm.data.model.LoginBody
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LoginViewModel(private val mainRepository: MainRepository,
                     private val networkHelper: NetworkHelper
) : ViewModel() {

    var loginResponse = MutableLiveData<Resource<DetailUserModel>>()

    fun login(loginBody: LoginBody){
        viewModelScope.launch(IO){
            loginResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.login(loginBody).let {
                    if (it.isSuccessful) {
                        loginResponse.postValue(Resource.success(it.body()))
                    } else {
                        loginResponse.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                loginResponse.postValue(Resource.error("No internet connection", null))
            }
        }
    }
}