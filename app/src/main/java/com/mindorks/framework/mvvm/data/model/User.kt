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
    @Json(name = "status") val status: Int? = 0,
    @Json(name = "message") val message: String? = ""
)

data class DepartmentsModel(
    @Json(name = "data") val data: ArrayList<DataResponseDepartment>? = ArrayList()
) : BaseModel()

@Parcelize
data class DataResponseDepartment(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("telephone") val telephone: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("status") val status: Int?,
    @SerializedName("price") val price: String?,
    @SerializedName("path_img") val image: String?,
    var active: Boolean?
) : Parcelable

data class EntalsGetMettingModel(@SerializedName("data") val data: ArrayList<Result>? = ArrayList()) :
    BaseModel()

data class Result(
    @SerializedName("id") var id: Long?,
    @SerializedName("meeting_room_id") var meetId: Long?,
    @SerializedName("user_id") var userId : String?
)

data class LoginBody(
    @Json(name = "fullname") val fullname: String,
    @Json(name = "password") val password: String
)

data class ResponseUser(
    @Json(name = "data") val lstUser: ArrayList<UserModel>?
) : BaseModel()

data class DetailUserModel(@SerializedName("data") val data: UserModel?) : BaseModel()

@Parcelize
data class UserModel(
    @SerializedName("id") val id: Int,
    @SerializedName("fullname") val fullname: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("activated") val activated: Int,
    @SerializedName("code") val code: String?,
    @SerializedName("address") val addresses: String?,
    @SerializedName("nationality") val nationality: String?,
    @SerializedName("gender") val gender: Int?,
    @SerializedName("id_card_no") val id_card_no: String?,
    @SerializedName("date_of_birth") val date_of_birth: String?,
    @SerializedName("issued_on") val issued_on: String?,
    @SerializedName("issued_at") val issued_at: String?,
    @SerializedName("department_id") val department_id: Int?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("marital_status") val marital_status: Int?,
    @SerializedName("ethnicity") val ethnicity: String?,
    @SerializedName("domicile") val domicile: String?,
    @SerializedName("admin") val admin: Int?,
) : Parcelable

data class EquipmentsResponse(@Json(name = "data") val data: ArrayList<EquipmentModel>?) :
    BaseModel()

data class EquipmentModel(
    @Json(name = "id") val id: Int,
    @Json(name = "type") val type: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "price") val price: Long?,
    @Json(name = "number") val number: Long?,
)