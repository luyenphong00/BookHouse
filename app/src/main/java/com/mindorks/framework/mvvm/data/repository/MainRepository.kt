package com.mindorks.framework.mvvm.data.repository

import com.mindorks.framework.mvvm.data.api.ApiService
import com.mindorks.framework.mvvm.data.model.LoginBody
import com.mindorks.framework.mvvm.data.model.UserModel

class MainRepository (private val apiHelper: ApiService) {

    suspend fun getUsers() =  apiHelper.getUsers()

    suspend fun getListUser() = apiHelper.getListUser()

    suspend fun updateUser(userModel: UserModel) = apiHelper.updateUser(userModel)

    suspend fun login(loginBody: LoginBody) = apiHelper.login(loginBody)

    suspend fun getDetailUser(id : String) = apiHelper.getUser(id)

    suspend fun fetChMeetingRoom() = apiHelper.meetingRooms()

    suspend fun getRentals(id : Int) = apiHelper.getRentals(id)

    suspend fun rentalsGetMettingRoom(id : Int) = apiHelper.rentalsGetMettingRoom(id)

}