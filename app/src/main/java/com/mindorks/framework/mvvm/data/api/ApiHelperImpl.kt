package com.mindorks.framework.mvvm.data.api

import com.mindorks.framework.mvvm.data.model.DetailUserModel
import com.mindorks.framework.mvvm.data.model.ResponseUser
import com.mindorks.framework.mvvm.data.model.User
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()
    override suspend fun getDetailUser(id: String): Response<DetailUserModel> = apiService.getUser(id)

    override suspend fun getListUsers(): Response<ResponseUser> = apiService.getListUser()
    override suspend fun login(): Response<String> = apiService.login()

}