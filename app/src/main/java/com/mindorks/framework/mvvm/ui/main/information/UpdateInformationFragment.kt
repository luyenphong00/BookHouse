package com.mindorks.framework.mvvm.ui.main.information

import android.widget.Toast
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.databinding.FragmentUpdateInforBinding
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.utils.Status
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class UpdateInformationFragment : CommonFragment<FragmentUpdateInforBinding,BaseViewModel>() {
    override val viewModel: InformationViewModel by viewModel()
    private val sharedViewModel by sharedViewModel<MainViewModel>()

    override fun getViewBinding(): FragmentUpdateInforBinding = FragmentUpdateInforBinding.inflate(layoutInflater)

    override fun initEvent() {
        super.initEvent()
        with(binding){
            save.setOnClickListener {
                viewModel.userModel?.phone = phone.text.toString()
                viewModel.userModel?.addresses = address.text.toString()
                viewModel.userModel?.id_card_no = cardNo.text.toString()
                viewModel.userModel?.nationality = nationality.text.toString()
                viewModel.userModel?.fullname = name.text.toString()
                viewModel.userModel?.email = email.text.toString()
                viewModel.userModel?.let {
                    viewModel.updateUser(it)
                }
            }
        }
    }

    override fun initData() {
        super.initData()
        sharedViewModel.userModel?.let {
            viewModel.userModel = sharedViewModel.userModel
            binding.name.setText(it.fullname)
            binding.email.setText(it.email)
            binding.address.setText(it.addresses)
            binding.phone.setText(it.phone)
            binding.nationality.setText(it.nationality)
            binding.cardNo.setText(it.id_card_no)
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.updateUserModel.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        getMainActivity()?.showLoading(false)
                        sharedViewModel.userModel = viewModel.userModel
                        Toast.makeText(requireContext(),"Cập nhật thành công", Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        getMainActivity()?.showLoading(false)
                        Toast.makeText(requireContext(),"Cập nhật thất bại", Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        getMainActivity()?.showLoading(true)
                    }
                }
            }
        }
    }

}