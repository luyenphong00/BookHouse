package com.mindorks.framework.mvvm.data.repository

import com.mindorks.framework.mvvm.data.api.ApiService
import com.mindorks.framework.mvvm.data.model.DataResponseDepartment
import com.mindorks.framework.mvvm.data.model.LoginBody
import com.mindorks.framework.mvvm.data.model.RoomBock
import com.mindorks.framework.mvvm.data.model.UserModel

class MainRepository (private val apiHelper: ApiService) {

    suspend fun getUsers() =  apiHelper.getUsers()

    suspend fun getListUser() = apiHelper.getListUser()

    suspend fun updateUser(userModel: UserModel) = apiHelper.updateUser(userModel)
    suspend fun rent(room: RoomBock) = apiHelper.rent(room)

    suspend fun login(loginBody: LoginBody) = apiHelper.login(loginBody)

    suspend fun register(loginBody: LoginBody) = apiHelper.register(loginBody)

    suspend fun getDetailUser(id : String) = apiHelper.getUser(id)

    suspend fun fetChMeetingRoom() = apiHelper.meetingRooms()

    suspend fun getRentals(id : Int) = apiHelper.getRentals(id)

    suspend fun fetchEquipments() = apiHelper.equipments()

    suspend fun services() = apiHelper.services()

    suspend fun rentalsGetMettingRoom(id : Int) = apiHelper.rentalsGetMettingRoom(id)

    suspend fun deleteRentals(id : Int) = apiHelper.deleteRentals(id)

    suspend fun deleteMeetingRooms(body : DataResponseDepartment) = apiHelper.deleteMeetingRooms(body)

    suspend fun updateMeetingRooms(body : DataResponseDepartment) = apiHelper.updateMeetingRooms(body)

}