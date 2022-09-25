package com.mindorks.framework.mvvm.ui.main.view

import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.common.CommonActivity
import com.mindorks.framework.mvvm.data.model.UserModel
import com.mindorks.framework.mvvm.databinding.ActivityMainBinding
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : CommonActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModel()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    fun showLoading(boolean: Boolean){
        binding.loading.isVisible = boolean
    }

    override fun initData() {
        super.initData()
        intent.extras?.getParcelable<UserModel>("param")?.let {
            viewModel.userModel = it
        }
    }

    override fun initEvent() {
        super.initEvent()
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.account -> {
                    Navigation.findNavController(this, R.id.nav_host).navigate(R.id.nav_account)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.metting -> {
                    Navigation.findNavController(this, R.id.nav_host).navigate(R.id.nav_metting)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    Navigation.findNavController(this, R.id.nav_host).navigate(R.id.action_information)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }

}
