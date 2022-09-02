package com.mindorks.framework.mvvm.ui.main

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonActivity
import com.mindorks.framework.mvvm.databinding.FragmentLoginBinding
import com.mindorks.framework.mvvm.ui.main.view.MainNewActivity

class LoginActivity : CommonActivity<BaseViewModel, FragmentLoginBinding>() {

    override val viewModel: ViewModel by viewModels()

    override fun getViewBinding(): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)

    override fun initEvent() {
        super.initEvent()
        with(binding){
            submit.setOnClickListener {
                startActivity(Intent(this@LoginActivity,MainNewActivity::class.java))
            }
        }
    }
}