package com.mindorks.framework.mvvm.data.repository

import com.mindorks.framework.mvvm.data.api.ApiHelper
import com.mindorks.framework.mvvm.data.model.LoginBody

class MainRepository (private val apiHelper: ApiHelper) {

    suspend fun getUsers() =  apiHelper.getUsers()

    suspend fun getListUser() = apiHelper.getListUsers()

    suspend fun login(loginBody: LoginBody) = apiHelper.login(loginBody)

    suspend fun getDetailUser(id : String) = apiHelper.getDetailUser(id)

    suspend fun fetChMeetingRoom() = apiHelper.fetChMeetingRoom()

    suspend fun getRentals(id : Int) = apiHelper.getRentals(id)

    suspend fun rentalsGetMettingRoom(id : Int) = apiHelper.rentalsGetMettingRoom(id)

}