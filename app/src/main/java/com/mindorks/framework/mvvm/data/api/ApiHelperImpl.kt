package com.mindorks.framework.mvvm.data.api

import com.mindorks.framework.mvvm.data.model.*
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()
    override suspend fun getDetailUser(id: String): Response<DetailUserModel> = apiService.getUser(id)

    override suspend fun getListUsers(): Response<ResponseUser> = apiService.getListUser()
    override suspend fun login(loginBody: LoginBody): Response<DetailUserModel> = apiService.login(loginBody)
    override suspend fun updateUser(userModel: UserModel): Response<UserModel> = apiService.updateUser(userModel)

    override suspend fun equipments(): Response<EquipmentsResponse> = apiService.equipments()
    override suspend fun services(): Response<EquipmentsResponse> = apiService.services()
    override suspend fun fetChMeetingRoom(): Response<DepartmentsModel> = apiService.meetingRooms()
    override suspend fun getRentals(id: Int): Response<DepartmentsModel> = apiService.getRentals(id)
    override suspend fun rentalsGetMettingRoom(id: Int): Response<EntalsGetMettingModel> = apiService.rentalsGetMettingRoom(id)

}