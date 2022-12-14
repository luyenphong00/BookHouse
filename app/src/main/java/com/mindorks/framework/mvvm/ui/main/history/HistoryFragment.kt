package com.mindorks.framework.mvvm.ui.main.history

import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.databinding.FragmentHistoryBinding
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.utils.Status
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryFragment : CommonFragment<FragmentHistoryBinding, BaseViewModel>() {

    private var historyAdapter: HistoryAdapter? = null
    override val viewModel: HistoryViewModel by viewModel()
    private var lstFake = mutableListOf<String>()
    private val sharedViewModel by sharedViewModel<MainViewModel>()

    override fun getViewBinding(): FragmentHistoryBinding =
        FragmentHistoryBinding.inflate(layoutInflater)

    override fun initData() {
        super.initData()
        sharedViewModel.userModel?.let {
            it.id?.let { it1 -> viewModel.getRentalsToMettingRoom(it1) }
        }
        historyAdapter = HistoryAdapter(requireContext()) {

        }
        binding.rclHistory.adapter = historyAdapter
    }

    override fun initView() {
        super.initView()
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.lstHistory.observe(viewLifecycleOwner) {
            it?.let { source ->
                when(source.status){
                    Status.SUCCESS -> {
                        getMainActivity()?.showLoading(false)
                        historyAdapter?.updateData(source.data?.data?: ArrayList())
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