package com.mindorks.framework.mvvm.ui.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.data.model.DataResponseDepartment
import com.mindorks.framework.mvvm.databinding.FragmentHomeBinding
import com.mindorks.framework.mvvm.ui.main.home.adapter.HouseAdapter
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.utils.Status
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : CommonFragment<FragmentHomeBinding, HomeViewModel>() {

    private var houseAdapterType1: HouseAdapter? = null
    private var houseAdapterType2: HouseAdapter? = null
    override val viewModel: HomeViewModel by viewModel()
    private val sharedViewModel by sharedViewModel<MainViewModel>()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun initData() {
        super.initData()
        sharedViewModel.userModel?.let {
            binding.tvName.text = it.email
        }

        viewModel.getMeetingRooms()

        houseAdapterType1 = HouseAdapter(requireContext(), 0) {
            val bundle = Bundle()
            bundle.putParcelable("obj",it)
            findNavController().navigate(R.id.action_nav_home_to_nav_detail,bundle)
        }

        houseAdapterType2 = HouseAdapter(requireContext(), 1) {
            findNavController().navigate(R.id.action_nav_home_to_nav_detail)
        }
        with(binding) {
            rclHouse.adapter = houseAdapterType1
        }
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
                    }
                    Status.ERROR -> {
                        getMainActivity()?.dismiss()
                    }
                    Status.LOADING -> {
                        getMainActivity()?.showLoading()
                    }
                }
            }
        }

        viewModel.lstHouseUpdate.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        getMainActivity()?.dismiss()
                        if (resource.data?.isNotEmpty()== true){
                            houseAdapterType1?.updateData(resource.data as ArrayList<DataResponseDepartment>)
                        }
                    }
                    Status.ERROR -> {
                        getMainActivity()?.dismiss()
                    }
                    Status.LOADING -> {
                        getMainActivity()?.showLoading()
                    }
                }
            }
        }
    }
}