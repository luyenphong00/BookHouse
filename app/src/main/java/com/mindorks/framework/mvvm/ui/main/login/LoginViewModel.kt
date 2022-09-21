package com.mindorks.framework.mvvm.ui.main.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                try {
                    val response = mainRepository.login(loginBody)
                    if (response.isSuccessful) {
                        loginResponse.postValue(Resource.success(response.body()))
                    } else {
                        loginResponse.postValue(Resource.error(response.errorBody().toString(), null))
                    }
                }catch (e : Exception){
                    loginResponse.postValue(Resource.error(e.message?:"", null))
                }
            } else {
                loginResponse.postValue(Resource.error("No internet connection", null))
            }
        }
    }

    fun register(loginBody: LoginBody){
        viewModelScope.launch(IO){
            loginResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = mainRepository.register(loginBody)
                    if (response.isSuccessful) {
                        loginResponse.postValue(Resource.success(response.body()))
                    } else {
                        loginResponse.postValue(Resource.error(response.errorBody().toString(), null))
                    }
                }catch (e : Exception){
                    loginResponse.postValue(Resource.error(e.message?:"", null))
                }
            } else {
                loginResponse.postValue(Resource.error("No internet connection", null))
            }
        }
    }
}