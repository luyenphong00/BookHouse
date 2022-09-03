package com.mindorks.framework.mvvm.ui.main.information

import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.databinding.FragmentHouseDetailBinding
import com.mindorks.framework.mvvm.databinding.FragmentInformationDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel


class UpdateInformationFragment : CommonFragment<FragmentInformationDetailBinding,BaseViewModel>() {
    override val viewModel: ViewModel by viewModel()

    override fun getViewBinding(): FragmentInformationDetailBinding = FragmentInformationDetailBinding.inflate(layoutInflater)
}