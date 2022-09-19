package com.mindorks.framework.mvvm.ui.main.viewmodel

import androidx.lifecycle.*
import com.mindorks.framework.mvvm.data.model.User
import com.mindorks.framework.mvvm.data.model.UserModel
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    var userModel : UserModel? = null

}