package com.mindorks.framework.mvvm.ui.main.manager.metting

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.data.model.DataResponseDepartment
import com.mindorks.framework.mvvm.databinding.FragmentMettingBinding
import com.mindorks.framework.mvvm.ui.main.home.HomeViewModel
import com.mindorks.framework.mvvm.ui.main.home.adapter.HouseAdapter
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.utils.Status
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MettingFragment : CommonFragment<FragmentMettingBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()
    private val sharedViewModel by sharedViewModel<MainViewModel>()
    private var houseAdapterType1: HouseAdapter? = null

    override fun getViewBinding(): FragmentMettingBinding =
        FragmentMettingBinding.inflate(layoutInflater)

    override fun initData() {
        super.initData()
        sharedViewModel.userModel?.let {
        }

        houseAdapterType1 = HouseAdapter(requireContext(), 0) {
            val bundle = Bundle()
            bundle.putParcelable("obj", it)
            findNavController().navigate(R.id.action_nav_detail, bundle)
        }

        with(binding) {
            rclHistory.adapter = houseAdapterType1
        }
        viewModel.getMeetingRooms()
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.meetingRoomsResponse.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { result ->
                            if (result.data?.isNotEmpty() == true){
                                viewModel.checkHouseActive()
                                houseAdapterType1?.updateData(result.data)
                            }
                        }
                        loading(false)

                    }
                    Status.ERROR -> {
                        loading(false)
                    }
                    Status.LOADING -> {
                        loading(true)
                    }
                }
            }
        }

        viewModel.lstHouseUpdate.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        getMainActivity()?.showLoading(false)
                        if (resource.data?.isNotEmpty()== true){
                            houseAdapterType1?.updateData(resource.data as ArrayList<DataResponseDepartment>)
                        }
                    }
                    Status.ERROR -> {
                        getMainActivity()?.showLoading(false)
                    }
                    Status.LOADING -> {
                        getMainActivity()?.showLoading(true)
                    }
                }
            }
        }
    }
}