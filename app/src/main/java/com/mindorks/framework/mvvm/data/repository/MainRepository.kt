package com.mindorks.framework.mvvm.data.repository

import com.mindorks.framework.mvvm.data.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper) {

    suspend fun getUsers() =  apiHelper.getUsers()

    suspend fun getListUser() = apiHelper.getListUsers()

    suspend fun login() = apiHelper.login()

    suspend fun getDetailUser(id : String) = apiHelper.getDetailUser(id)

}