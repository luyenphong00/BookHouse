package com.mindorks.framework.mvvm.ui.main.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.databinding.FragmentHomeBinding

class HomeFragment : CommonFragment<FragmentHomeBinding,BaseViewModel>() {
    override val viewModel: ViewModel by viewModels()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
}