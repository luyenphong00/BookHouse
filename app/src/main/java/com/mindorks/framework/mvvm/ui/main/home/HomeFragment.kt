package com.mindorks.framework.mvvm.ui.main.home

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.databinding.FragmentHomeBinding
import com.mindorks.framework.mvvm.ui.main.home.adapter.HouseAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : CommonFragment<FragmentHomeBinding,BaseViewModel>() {

    private var houseAdapterType1 : HouseAdapter? = null
    private var houseAdapterType2 : HouseAdapter? = null
    private var lstFake = mutableListOf<String>()
    override val viewModel: ViewModel by viewModel()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun initData() {
        super.initData()
        houseAdapterType1 = HouseAdapter(requireContext(), 0) {
            findNavController().navigate(R.id.action_nav_home_to_nav_detail)
        }

        houseAdapterType2 = HouseAdapter(requireContext(),1){

        }
        with(binding){
            rclHouse.adapter = houseAdapterType1
            rclHouseNew.adapter = houseAdapterType2
        }
        getMainActivity()?.showLoading()
    }

    override fun initView() {
        super.initView()
        lstFake.add("")
        lstFake.add("")
        lstFake.add("")
        lstFake.add("")
        lstFake.add("")
        lstFake.add("")
        lstFake.add("")
        lstFake.add("")
        lstFake.add("")
        lstFake.add("")
        houseAdapterType1?.updateData(lstFake as ArrayList<String>)
        houseAdapterType2?.updateData(lstFake as ArrayList<String>)
        Handler(Looper.getMainLooper()).postDelayed({
            getMainActivity()?.dismiss()
        }, 2000)

    }
}