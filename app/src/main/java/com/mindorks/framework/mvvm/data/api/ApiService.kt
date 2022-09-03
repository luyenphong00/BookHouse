package com.mindorks.framework.mvvm.data.api

import com.mindorks.framework.mvvm.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    //
    suspend fun login() : Response<String>

    // http://127.0.0.1:8000/api/users
    @GET("users")
    suspend fun getListUser() : Response<ResponseUser>

    // http://127.0.0.1:8000/api/users/1
    @GET("users")
    suspend fun getUser(@Query("id") id : String) : Response<DetailUserModel>

    // http://127.0.0.1:8000/api/users/1?addresses=HaNoi&phone=0386592323
    @PUT("users")
    suspend fun updateUser(@Body userModel: UserModel) : Response<UserModel>

    // http://127.0.0.1:8000/api/departments
    @GET("departments")
    suspend fun getDepartments() : Response<DepartmentsModel>

    // http://127.0.0.1:8000/api/departments/1
    @GET("departments")
    suspend fun getDetailDepartments(@Query("id") id : String) : Response<DepartmentsModel>


}