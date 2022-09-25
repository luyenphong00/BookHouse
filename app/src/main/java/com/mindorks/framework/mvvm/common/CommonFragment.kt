package com.mindorks.framework.mvvm.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.mindorks.framework.mvvm.ui.main.view.MainActivity
import com.mindorks.framework.mvvm.ui.main.view.MainNewActivity

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

    protected open fun getMainActivity(): MainNewActivity? {
        return if (activity is MainNewActivity) {
            activity as MainNewActivity?
        } else null
    }

    protected open fun mainActivity(): MainActivity? {
        return if (activity is MainActivity) {
            activity as MainActivity?
        } else null
    }

    fun showLoading(boolean: Boolean){
        getMainActivity()?.showLoading(boolean)
    }

    fun loading(boolean: Boolean){
        mainActivity()?.showLoading(boolean)
    }

    fun showMessage(message : String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

}