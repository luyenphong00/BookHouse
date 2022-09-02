package com.mindorks.framework.mvvm.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class CommonFragment<VB : ViewBinding,VM : BaseViewModel> : Fragment(), IScreen {

    protected abstract val viewModel: ViewModel

    lateinit var binding: VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initEvent()
        initObserver()
    }

    abstract fun getViewBinding(): VB

    override fun initData() {
    }

    override fun initView() {

    }

    override fun initEvent() {
    }

    override fun initObserver() {
    }

}