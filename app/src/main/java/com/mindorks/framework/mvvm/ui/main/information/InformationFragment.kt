package com.mindorks.framework.mvvm.ui.main.information

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.databinding.FragmentHomeBinding
import com.mindorks.framework.mvvm.databinding.FragmentInfomationBinding

class InformationFragment : CommonFragment<FragmentInfomationBinding,BaseViewModel>() {
    override val viewModel: ViewModel by viewModels()

    override fun getViewBinding(): FragmentInfomationBinding = FragmentInfomationBinding.inflate(layoutInflater)
}