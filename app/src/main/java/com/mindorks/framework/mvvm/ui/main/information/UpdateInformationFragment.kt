package com.mindorks.framework.mvvm.ui.main.information

import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.databinding.FragmentInformationDetailBinding
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.utils.Status
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class UpdateInformationFragment : CommonFragment<FragmentInformationDetailBinding,BaseViewModel>() {
    override val viewModel: InformationViewModel by viewModel()
    private val sharedViewModel by sharedViewModel<MainViewModel>()

    override fun getViewBinding(): FragmentInformationDetailBinding = FragmentInformationDetailBinding.inflate(layoutInflater)

    override fun initEvent() {
        super.initEvent()
        with(binding){
            save.setOnClickListener {
//                viewModel.updateUser()
            }
        }
    }

    override fun initData() {
        super.initData()
        sharedViewModel.userModel?.let {
            binding.name.setText(it.fullname)
            binding.email.setText(it.email)
            binding.address.setText(it.addresses)
            binding.phone.setText(it.phone)
            binding.nationality.text = it.nationality
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.updateUserModel.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        getMainActivity()?.dismiss()
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