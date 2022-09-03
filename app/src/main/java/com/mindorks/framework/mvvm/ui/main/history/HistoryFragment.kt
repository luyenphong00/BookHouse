package com.mindorks.framework.mvvm.ui.main.history

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.databinding.FragmentHistoryBinding

class HistoryFragment : CommonFragment<FragmentHistoryBinding,BaseViewModel>() {

    private var historyAdapter : HistoryAdapter? = null
    override val viewModel: ViewModel by viewModels()
    private var lstFake = mutableListOf<String>()

    override fun getViewBinding(): FragmentHistoryBinding = FragmentHistoryBinding.inflate(layoutInflater)

    override fun initData() {
        super.initData()
        historyAdapter = HistoryAdapter(requireContext()) {

        }
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
        binding.rclHistory.adapter = historyAdapter
        historyAdapter?.updateData(lstFake as ArrayList<String>)
    }

    override fun initView() {
        super.initView()
    }
}