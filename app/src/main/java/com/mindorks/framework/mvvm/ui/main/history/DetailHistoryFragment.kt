package com.mindorks.framework.mvvm.ui.main.history

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.databinding.FragmentHistoryBinding

class DetailHistoryFragment : CommonFragment<FragmentHistoryBinding,BaseViewModel>() {

    private var historyAdapter : HistoryAdapter? = null
    override val viewModel: ViewModel by viewModels()
    private var lstFake = mutableListOf<String>()

    override fun getViewBinding(): FragmentHistoryBinding = FragmentHistoryBinding.inflate(layoutInflater)

    override fun initData() {
        super.initData()
        historyAdapter = HistoryAdapter(requireContext()) {

        }

        binding.rclHistory.adapter = historyAdapter
    }

    override fun initView() {
        super.initView()
    }
}