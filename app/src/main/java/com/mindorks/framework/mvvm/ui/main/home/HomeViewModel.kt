package com.mindorks.framework.mvvm.ui.main.home

import androidx.lifecycle.*
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.data.model.DataResponseDepartment
import com.mindorks.framework.mvvm.data.model.DepartmentsModel
import com.mindorks.framework.mvvm.data.model.User
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
    val meetingRoomsResponse = MutableLiveData<Resource<DepartmentsModel>>()
    val lstHouseUpdate = MutableLiveData<Resource<MutableList<DataResponseDepartment>>>()

    fun getMeetingRooms() {
        viewModelScope.launch(IO) {
            meetingRoomsResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.fetChMeetingRoom().let {
                    if (it.isSuccessful) {
                        it.body()?.let { departMent ->
                            departMent.data?.let { list ->
                                if (list.isNotEmpty()) {
                                    lstHouseHome.clear()
                                    lstHouseHome.addAll(list)
                                }
                            }
                        }
                        meetingRoomsResponse.postValue(Resource.success(it.body()))
                    } else meetingRoomsResponse.postValue(
                        Resource.error(
                            it.errorBody().toString(),
                            null
                        )
                    )
                }
            } else meetingRoomsResponse.postValue(Resource.error("No internet connection", null))
        }
    }

    fun checkHouseActive() {
        viewModelScope.launch(IO) {
            lstHouseUpdate.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                supervisorScope {
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
                        lstHouseUpdate.postValue(Resource.success(null))
                    }
                }
            } else lstHouseUpdate.postValue(Resource.error("No internet connection", null))
        }
    }

}