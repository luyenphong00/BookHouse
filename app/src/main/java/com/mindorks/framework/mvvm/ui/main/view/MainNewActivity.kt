package com.mindorks.framework.mvvm.ui.main.view

import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonActivity
import com.mindorks.framework.mvvm.databinding.ActivityMainNewBinding

class MainNewActivity : CommonActivity<BaseViewModel,ActivityMainNewBinding>() {
    override val viewModel: ViewModel by viewModels()

    override fun getViewBinding(): ActivityMainNewBinding = ActivityMainNewBinding.inflate(layoutInflater)

    override fun initEvent() {
        super.initEvent()
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.action_home)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.message -> {
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.action_information)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }
}