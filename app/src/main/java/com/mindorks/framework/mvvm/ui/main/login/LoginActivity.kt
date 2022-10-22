package com.mindorks.framework.mvvm.ui.main.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonActivity
import com.mindorks.framework.mvvm.data.model.LoginBody
import com.mindorks.framework.mvvm.databinding.FragmentLoginBinding
import com.mindorks.framework.mvvm.ui.main.view.MainActivity
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
                if (binding.fullname.text.toString().isNotEmpty()
                    && binding.pass.text.toString().isNotEmpty()) {
                    viewModel.login(LoginBody(binding.fullname.text.toString(),
                        binding.pass.text.toString()))
                }else {
                    showToast("Nhập đủ thông tin!")
                }
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
                        resource.data?.let {
                            val bundler = Bundle()
                            it.data?.let { userModel ->
                                var intent: Intent? = null
                                intent = Intent(this@LoginActivity, MainActivity::class.java)
                                bundler.putParcelable("param", resource.data.data)
                                intent.putExtras(bundler)
                                startActivity(intent)
                                finish()
                            }

                        } ?: kotlin.run {
                            showToast(resource.message.toString())
                        }

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

    fun showLoading(boolean: Boolean) {
        binding.loading.isVisible = boolean
    }
}