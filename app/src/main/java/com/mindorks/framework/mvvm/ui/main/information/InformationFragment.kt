package com.mindorks.framework.mvvm.ui.main.information

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.databinding.FragmentHomeBinding
import com.mindorks.framework.mvvm.databinding.FragmentInfomationBinding

class InformationFragment : CommonFragment<FragmentInfomationBinding,BaseViewModel>() {
    override val viewModel: ViewModel by viewModels()

    override fun getViewBinding(): FragmentInfomationBinding = FragmentInfomationBinding.inflate(layoutInflater)

    override fun initEvent() {
        super.initEvent()
        with(binding){
            updateInfo.setOnClickListener {
                findNavController().navigate(R.id.action_nav_information_to_nav_update_info)
            }
        }
    }
}