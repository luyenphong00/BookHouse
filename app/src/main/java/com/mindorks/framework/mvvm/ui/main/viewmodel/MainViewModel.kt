package com.mindorks.framework.mvvm.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvvm.data.model.UserModel

class MainViewModel() : ViewModel() {

    var userModel : UserModel? = null
    var result : com.mindorks.framework.mvvm.data.model.Result? = null

}