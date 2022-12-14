package com.mindorks.framework.mvvm.ui.main.view

import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.common.CommonActivity
import com.mindorks.framework.mvvm.data.model.DetailUserModel
import com.mindorks.framework.mvvm.data.model.UserModel
import com.mindorks.framework.mvvm.databinding.ActivityMainNewBinding
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.item_house.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainNewActivity : CommonActivity<MainViewModel,ActivityMainNewBinding>() {
    override val viewModel: MainViewModel by viewModel()

    override fun getViewBinding(): ActivityMainNewBinding = ActivityMainNewBinding.inflate(layoutInflater)

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
                R.id.home -> {
                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.action_home)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.message -> {
                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.action_history)
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

    fun showLoading(boolean: Boolean){
        binding.loading.isVisible = boolean
    }

}