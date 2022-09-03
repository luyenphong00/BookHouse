package com.mindorks.framework.mvvm.data.model

import com.squareup.moshi.Json
import org.koin.core.qualifier.named


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
    @Json(name = "data") val lstUser: ArrayList<DataResponseDepartment>?
) : BaseModel()

data class DataResponseDepartment(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val fullName: String?,
    @Json(name = "telephone") val telephone: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "status") val status: Int?,
    @Json(name = "created_at") val created_at: Int?,
)

data class ResponseUser(
    @Json(name = "data") val lstUser: ArrayList<UserModel>?
) : BaseModel()

data class DetailUserModel(@Json(name = "data") val userModel: UserModel?) : BaseModel()

data class UserModel(
    @Json(name = "id") val id: Int,
    @Json(name = "fullname") val fullName: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "activated") val activated: Int,
    @Json(name = "code") val code: String?,
    @Json(name = "address") val addresses: String?,
    @Json(name = "nationality") val nationality: String?,
    @Json(name = "gender") val gender: Int?,
    @Json(name = "id_card_no") val id_card_no: String?,
    @Json(name = "date_of_birth") val date_of_birth: String?,
    @Json(name = "issued_on") val issued_on: String?,
    @Json(name = "issued_at") val issued_at: String?,
    @Json(name = "department_id") val department_id: Int?,
    @Json(name = "phone") val phone: String?,
    @Json(name = "marital_status") val marital_status: Int?,
    @Json(name = "ethnicity") val ethnicity: String?,
    @Json(name = "domicile") val domicile: String?,
    @Json(name = "admin") val admin: Int?,
)