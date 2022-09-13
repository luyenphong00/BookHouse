package com.mindorks.framework.mvvm.data.api

import com.mindorks.framework.mvvm.data.model.*
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(): Response<List<User>>

    suspend fun getDetailUser(id : String) : Response<DetailUserModel>

    suspend fun getListUsers(): Response<ResponseUser>

    suspend fun login(loginBody: LoginBody) : Response<DetailUserModel>

    suspend fun updateUser(userModel : UserModel) : Response<UserModel>

    suspend fun equipments() : Response<EquipmentsResponse>

    suspend fun services() : Response<EquipmentsResponse>

    suspend fun fetChMeetingRoom() : Response<DepartmentsModel>

    suspend fun getRentals(id : Int) : Response<DepartmentsModel>

    suspend fun rentalsGetMettingRoom(id : Int) : Response<EntalsGetMettingModel>

}