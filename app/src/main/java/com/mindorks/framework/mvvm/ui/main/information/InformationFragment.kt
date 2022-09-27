package com.mindorks.framework.mvvm.ui.main.information

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.data.model.UserModel
import com.mindorks.framework.mvvm.databinding.FragmentHomeBinding
import com.mindorks.framework.mvvm.databinding.FragmentInfomationBinding
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class InformationFragment : CommonFragment<FragmentInfomationBinding, BaseViewModel>() {
    override val viewModel: InformationViewModel by viewModels()
    private val sharedViewModel by sharedViewModel<MainViewModel>()

    override fun getViewBinding(): FragmentInfomationBinding =
        FragmentInfomationBinding.inflate(layoutInflater)

    override fun initData() {
        super.initData()
        with(binding) {
            sharedViewModel.userModel?.let {
                name.text = it.fullname
                email.text = it.email
                updateView(it)
            }
        }
    }

    fun updateView(userModel : UserModel){
        if (userModel.admin == 1){
            binding.managerDevice.visibility = View.VISIBLE
            binding.managerService.visibility = View.VISIBLE
        }else {
            binding.managerDevice.visibility = View.GONE
            binding.managerService.visibility = View.GONE
        }
    }

    override fun initEvent() {
        super.initEvent()
        with(binding) {
            updateInfo.setOnClickListener {
                findNavController().navigate(R.id.action_nav_information_to_nav_update_info)
            }
            logout.setOnClickListener {
                activity?.finish()
            }
        }
    }
}