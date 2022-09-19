package com.mindorks.framework.mvvm.di.module

import com.mindorks.framework.mvvm.ui.main.history.HistoryViewModel
import com.mindorks.framework.mvvm.ui.main.home.HomeViewModel
import com.mindorks.framework.mvvm.ui.main.information.InformationViewModel
import com.mindorks.framework.mvvm.ui.main.login.LoginViewModel
import com.mindorks.framework.mvvm.ui.main.viewmodel.DetailModel
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel()
    }

    viewModel {
        DetailModel(get(),get())
    }
    viewModel {
        LoginViewModel(get(),get())
    }

    viewModel {
        InformationViewModel(get(),get())
    }

    viewModel {
        HomeViewModel(get(),get())
    }

    viewModel {
        HistoryViewModel(get(),get())
    }
}