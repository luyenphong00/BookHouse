package com.mindorks.framework.mvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


data class User(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "avatar")
    val avatar: String = ""
)

open class BaseModel(
    @SerializedName("status") val status: Int? = 0,
    @SerializedName("message") val message: String? = ""
)

data class DepartmentsModel(
    @Json(name = "data") val data: ArrayList<DataResponseDepartment>? = ArrayList()
) : BaseModel()

@Parcelize
data class DataResponseDepartment(
    @SerializedName("id") val id: Int,
    @SerializedName("name") var name: String?,
    @SerializedName("telephone") var telephone: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("status") var status: Int?,
    @SerializedName("price") var price: String?,
    @SerializedName("path_img") val image: String?,
    var active: Boolean?
) : Parcelable

data class EntalsGetMettingModel(@SerializedName("data") val data: ArrayList<Result>? = ArrayList()) :
    BaseModel()

data class Result(
    @SerializedName("id") var id: Long?,
    @SerializedName("meeting_room_id") var meetId: Long?,
    @SerializedName("user_id") var userId: String?,
    @SerializedName("total_money") var totalMoney: String?,
    @SerializedName("rental_services") var rentalServices: ArrayList<RentalServices>?,
    @SerializedName("name_meeting_room") var nameRoom: String?,
    @SerializedName("path_img_meeting_room") var path: String?,
)

data class RentalServices(
    @SerializedName("id") var id: Long?,
    @SerializedName("service_id") var service_id: Long?,
    @SerializedName("quantity") var quantity: Long?,
    @SerializedName("rental_history_id") var rentalHistoryId: Long?,
    @SerializedName("created_at") var createdAt: String?,
)


data class LoginBody(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)

data class ResponseUser(
    @SerializedName("data") val lstUser: ArrayList<UserModel>?
) : BaseModel()

data class DetailUserModel(@SerializedName("data") val data: UserModel?) : BaseModel()

@Parcelize
data class UserModel(
    @SerializedName("id") var id: Int?,
    @SerializedName("fullname") var fullname: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("addresses") var addresses: String?,
    @SerializedName("nationality") var nationality: String?,
    @SerializedName("gender") var gender: Int?,
    @SerializedName("id_card_no") var id_card_no: String?,
    @SerializedName("date_of_birth") var date_of_birth: String?,
    @SerializedName("issued_on") var issued_on: String?,
    @SerializedName("issued_at") var issued_at: String?,
    @SerializedName("department_id") var department_id: Int?,
    @SerializedName("phone") var phone: String?,
    @SerializedName("marital_status") var marital_status: Int?,
    @SerializedName("ethnicity") var ethnicity: String?,
    @SerializedName("domicile") var domicile: String?,
    @SerializedName("admin") var admin: Int?,
    var select : Boolean? = false
) : Parcelable

data class EquipmentsResponse(@Json(name = "data") val data: ArrayList<EquipmentModel>?) :
    BaseModel()

data class EquipmentModel(
    @Json(name = "id") val id: Int,
    @Json(name = "type") val type: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "price") val price: Long?,
    @Json(name = "number") val number: Long?,
    var select: Boolean = false,
    var count: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EquipmentModel

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}

// dat phong

data class RoomBock(
    @SerializedName("meeting_room_id") var meetingRoomId: Int?,
    @SerializedName("user_id") var userId: String?,
    @SerializedName("rental_services") var rentalServices: ArrayList<RentalServicesRequest>?,
    @SerializedName("rental_equipments") var rentalEquipment: ArrayList<RentalEquipmentsRequest>?,
    @SerializedName("hour_start") var hour_start: String = "2022-09-03 21:52:40",
    @SerializedName("hour_end") var hour_end: String = "2022-09-20 21:52:41",
    @SerializedName("date") var date: String = "2022-09-20 21:52:41",
    @SerializedName("status") var status: Int = 1,
)

data class ResponseRoomBock(@SerializedName("data") var data: String?) : BaseModel()

data class RentalServicesRequest(
    @SerializedName("service_id") var service_id: Int? = 0,
    @SerializedName("quantity") var quantity: Int? = 0,
)

data class RentalEquipmentsRequest(
    @SerializedName("equipment_id") var service_id: Int? = 0,
    @SerializedName("quantity") var quantity: Int? = 0,
)
