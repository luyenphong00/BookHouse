package com.mindorks.framework.mvvm.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.mindorks.framework.mvvm.databinding.DialogChecklinkBinding
import com.mindorks.framework.mvvm.ui.main.dialog.DialogCheckLink

abstract class CommonActivity<VM : ViewModel, B : ViewBinding> : AppCompatActivity(), IScreen {

    lateinit var binding: B
    protected abstract val viewModel: ViewModel
    protected var dialog : DialogCheckLink? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        dialog = DialogCheckLink(this)
        initData()
        initView()
        initEvent()
        initObserver()
    }

    override fun initData() {
    }

    override fun initEvent() {
    }

    override fun initObserver() {
    }

    override fun initView() {
    }

    abstract fun getViewBinding(): B

    fun showLoading(){
        dialog?.show()
    }

    fun dismiss(){
        dialog?.dismiss()
    }

}