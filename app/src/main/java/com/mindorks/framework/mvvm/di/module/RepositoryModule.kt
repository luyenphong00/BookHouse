package com.mindorks.framework.mvvm.di.module

import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.ui.main.information.InformationViewModel
import com.mindorks.framework.mvvm.ui.main.login.LoginViewModel
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }

    single {
        LoginViewModel(get(),get())
    }

    single {
        InformationViewModel(get(),get())
    }
}