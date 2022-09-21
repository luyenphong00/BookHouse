package com.mindorks.framework.mvvm.ui.main.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonActivity
import com.mindorks.framework.mvvm.data.model.LoginBody
import com.mindorks.framework.mvvm.databinding.FragmentLoginBinding
import com.mindorks.framework.mvvm.ui.main.view.MainNewActivity
import com.mindorks.framework.mvvm.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : CommonActivity<BaseViewModel, FragmentLoginBinding>() {

    override val viewModel: LoginViewModel by viewModel()

    override fun getViewBinding(): FragmentLoginBinding =
        FragmentLoginBinding.inflate(layoutInflater)

    override fun initEvent() {
        super.initEvent()
        with(binding) {
            submit.setOnClickListener {
                viewModel.login(LoginBody("Admin", "123456"))
            }

            register.setOnClickListener {
                viewModel.register(LoginBody("TKTest", "123456"))
            }
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.loginResponse.observe(this) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showLoading(false)
                        val intent = Intent(this@LoginActivity,MainNewActivity::class.java)
                        val bundler = Bundle()
                        bundler.putParcelable("param",resource.data?.data)
                        intent.putExtras(bundler)
                        startActivity(intent)
                        finish()
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showToast(resource.message.toString())
                    }
                    Status.LOADING -> {
                        showLoading(true)
                    }
                }
            }
        }
    }

    fun showLoading(boolean: Boolean){
        binding.loading.isVisible = boolean
    }
}