package com.mindorks.framework.mvvm.ui.main.home.adapter

import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.databinding.FragmentHouseDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel


class HouseDetailFragment : CommonFragment<FragmentHouseDetailBinding,BaseViewModel>() {
    override val viewModel: ViewModel by viewModel()

    override fun getViewBinding(): FragmentHouseDetailBinding = FragmentHouseDetailBinding.inflate(layoutInflater)
}