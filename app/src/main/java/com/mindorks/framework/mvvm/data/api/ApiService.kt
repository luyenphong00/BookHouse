package com.mindorks.framework.mvvm.data.api

import com.mindorks.framework.mvvm.data.model.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    //
    @POST("login")
    suspend fun login(@Body loginBody: LoginBody) : Response<DetailUserModel>

    @POST("users")
    suspend fun register(@Body loginBody: LoginBody) : Response<DetailUserModel>

    // http://127.0.0.1:8000/api/users
    @GET("users")
    suspend fun getListUser() : Response<ResponseUser>

    // http://127.0.0.1:8000/api/users/1
    @GET("users")
    suspend fun getUser(@Query("id") id : String) : Response<DetailUserModel>

    // http://127.0.0.1:8000/api/users/1?addresses=HaNoi&phone=0386592323
    @POST("users")
    suspend fun updateUser(@Body userModel: UserModel) : Response<DetailUserModel>

    // thue phong tro
    @POST("rentals")
    suspend fun rent(@Body roomBock: RoomBock) : Response<ResponseBody>

    // http://127.0.0.1:8000/api/departments
    @GET("departments")
    suspend fun getDepartments() : Response<DepartmentsModel>

    // http://127.0.0.1:8000/api/departments/1
    @GET("departments")
    suspend fun getDetailDepartments(@Query("id") id : String) : Response<DepartmentsModel>

    // API List equipments - Chi tiết- http://127.0.0.1:8000/api/equipments
    @GET("equipments")
    suspend fun equipments() : Response<EquipmentsResponse>

    // http://127.0.0.1:8000/api/users/1?addresses=HaNoi&phone=0386592323
    @PUT("users")
    suspend fun updateEquipments(@Body equipmentModel: EquipmentModel) : Response<UserModel>

    // API List services - Chi tiết- http://127.0.0.1:8000/api/services
    @GET("services")
    suspend fun services() : Response<EquipmentsResponse>

    // http://192.168.43.193/DoAnDuongDucThang/server.php/api/meeting-rooms
    @GET("meeting-rooms")
    suspend fun meetingRooms() : Response<DepartmentsModel>

    // http://127.0.0.1:8000/api/rentals?meeting_room_id=1
    @GET("rentals")
    suspend fun getRentals(@Query("meeting_room_id") id : Int) : Response<DepartmentsModel>

    @DELETE("rentals")
    suspend fun deleteRentals(@Query("id") id : Int) : Response<BaseModel>

    // http://127.0.0.1:8000/api/rentals_get_metting_room_of_user?user_id=1
    @GET("rentals_get_metting_room_of_user")
    suspend fun  rentalsGetMettingRoom(@Query("user_id") id : Int) : Response<EntalsGetMettingModel>

}