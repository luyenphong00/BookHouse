package com.mindorks.framework.mvvm.ui.main.manager

import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.databinding.FragmentAccountBinding
import com.mindorks.framework.mvvm.ui.main.adapter.AccountAdapter
import com.mindorks.framework.mvvm.ui.main.viewmodel.DetailModel
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.utils.Status
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AccountFragment : CommonFragment<FragmentAccountBinding, DetailModel>() {

    override val viewModel: DetailModel by viewModel()
    private val sharedViewModel by sharedViewModel<MainViewModel>()
    private var adapter : AccountAdapter? = null

    override fun getViewBinding(): FragmentAccountBinding = FragmentAccountBinding.inflate(layoutInflater)

    override fun initData() {
        super.initData()
        sharedViewModel.userModel?.let {
            binding.tvName.text = it.email
        }

        adapter = AccountAdapter(requireContext()) {
            viewModel.deleteUser(it)
        }
        with(binding){
            rclHouse.adapter = adapter
        }
        viewModel.getListUser()
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.lstUserLiveData.observe(viewLifecycleOwner) {
            it?.let { source ->
                when(source.status){
                    Status.SUCCESS -> {
                        loading(false)
                        source.data?.let { response ->
                            if (response.lstUser?.isNotEmpty() == true){
                                adapter?.updateData(response.lstUser)
                                sharedViewModel.userModel?.let { it1 -> adapter?.update(it1) }
                            }
                        }
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

        viewModel.deleteUserLiveData.observe(viewLifecycleOwner) {
            it?.let { source ->
                when(source.status){
                    Status.SUCCESS -> {
                        loading(false)
                        showMessage("Xóa thành công")
                    }
                    Status.ERROR -> {
                        showMessage("Xóa thành công")
                        loading(false)
                    }
                    Status.LOADING -> {
                        loading(true)
                    }
                }
            }

        }
    }
}