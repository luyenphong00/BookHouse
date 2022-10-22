package com.mindorks.framework.mvvm.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.data.model.*
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class HomeViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>>
        get() = _users

    val lstHouseHome = mutableListOf<DataResponseDepartment>()
    val lstHouseSearch = mutableListOf<DataResponseDepartment>()
    val meetingRoomsResponse = MutableLiveData<Resource<DepartmentsModel>>()
    val lstHouseUpdate = MutableLiveData<Resource<MutableList<DataResponseDepartment>>>()
    val lstSearch = MutableLiveData<Resource<MutableList<DataResponseDepartment>>>()

    fun getMeetingRooms() {
        viewModelScope.launch(IO) {
            meetingRoomsResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = mainRepository.fetChMeetingRoom()
                    response.let {
                        if (it.isSuccessful) {
                            it.body()?.let { body ->
                                body.data?.let { list ->
                                    if (list.isNotEmpty()) {
                                        lstHouseHome.clear()
                                        lstHouseHome.addAll(list)
                                    }
                                }
                            }
                            meetingRoomsResponse.postValue(Resource.success(it.body()))
                        } else meetingRoomsResponse.postValue(
                            Resource.error(it.errorBody().toString(), null)
                        )
                    }
                } catch (e: Exception) {
                    meetingRoomsResponse.postValue(Resource.error(e.toString(), null))
                }
            } else meetingRoomsResponse.postValue(Resource.error("No internet connection", null))
        }
    }

    fun checkHouseActive() {
        viewModelScope.launch(IO) {
            lstHouseUpdate.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                supervisorScope {
                    try {
                        if (lstHouseHome.isNotEmpty()) {
                            lstHouseHome.forEach {
                                val response = mainRepository.getRentals(it.id)
                                response.let { reponse ->
                                    if (reponse.isSuccessful) {
                                        it.active = reponse.body()?.data?.isNotEmpty() != true
                                    } else {
                                        it.active = true
                                    }
                                }
                            }
                            lstHouseUpdate.postValue(Resource.success(lstHouseHome))
                        } else {
                            lstHouseUpdate.postValue(Resource.error("No internet connection", null))
                        }
                    } catch (e: Exception) {
                        lstHouseUpdate.postValue(Resource.error(e.message ?: "", null))
                    }
                }
            } else lstHouseUpdate.postValue(Resource.error("No internet connection", null))
        }
    }

    fun search(body: SearchRequest) {
        viewModelScope.launch(IO) {
            lstSearch.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = mainRepository.search(body)
                    response.let {
                        if (it.isSuccessful) {
                            it.body()?.let { body ->
                                body.data?.let { list ->
                                    if (list.isNotEmpty()) {
                                        lstHouseSearch.clear()
                                        lstHouseSearch.addAll(list)
                                    }
                                }
                            }
                            lstSearch.postValue(Resource.success(lstHouseSearch))
                        } else {
                            val errorModel = Gson().fromJson(
                                response.errorBody().toString(),
                                BaseModel::class.java
                            )
                            lstSearch.postValue(
                                Resource.error(errorModel.message?:"", null)
                            )
                        }
                    }
                } catch (e: Exception) {
                    lstSearch.postValue(Resource.error(e.toString(), null))
                }
            } else lstSearch.postValue(Resource.error("No internet connection", null))
        }
    }

}