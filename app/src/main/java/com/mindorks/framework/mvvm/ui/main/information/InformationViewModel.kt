package com.mindorks.framework.mvvm.ui.main.information

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindorks.framework.mvvm.data.model.BaseModel
import com.mindorks.framework.mvvm.data.model.DetailUserModel
import com.mindorks.framework.mvvm.data.model.UserModel
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class InformationViewModel(private val mainRepository: MainRepository,
                           private val networkHelper: NetworkHelper
) : ViewModel() {

    var userModel : UserModel? = null

    var updateUserModel = MutableLiveData<Resource<DetailUserModel>>()

    fun updateUser(userModel: UserModel){
        viewModelScope.launch(IO){
            updateUserModel.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.updateUser(userModel).let {
                    if (it.isSuccessful){
                        updateUserModel.postValue(Resource.success(it.body()))
                    }else {
                        updateUserModel.postValue(Resource.error("",null))
                    }
                }
            } else updateUserModel.postValue(Resource.error("No internet connection", null))
        }
    }

}