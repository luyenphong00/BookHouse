package com.mindorks.framework.mvvm.ui.main.history

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.databinding.FragmentHistoryBinding
import com.mindorks.framework.mvvm.databinding.FragmentHouseDetailBinding

class DetailHistoryFragment : CommonFragment<FragmentHouseDetailBinding,BaseViewModel>() {

    private var historyAdapter : HistoryAdapter? = null
    override val viewModel: ViewModel by viewModels()
    private var lstFake = mutableListOf<String>()

    override fun getViewBinding(): FragmentHouseDetailBinding = FragmentHouseDetailBinding.inflate(layoutInflater)

    override fun initData() {
        super.initData()
        historyAdapter = HistoryAdapter(requireContext(),{}) {
            val bundle = Bundle()
//            bundle.putParcelable("obj",)
            findNavController().navigate(R.id.action_nav_home_to_nav_detail,bundle)
        }

//        binding.rclHistory.adapter = historyAdapter
    }

    override fun initView() {
        super.initView()
    }
}