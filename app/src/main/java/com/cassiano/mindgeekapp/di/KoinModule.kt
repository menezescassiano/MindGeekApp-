package com.cassiano.mindgeekapp.di

import com.cassiano.mindgeekapp.home.view.viewmodel.SettingsViewModel
import com.cassiano.mindgeekapp.password.view.viewmodel.FirstAttemptPasswordViewModel
import com.cassiano.mindgeekapp.password.view.viewmodel.SecondAttemptPasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { SettingsViewModel() }
    viewModel { FirstAttemptPasswordViewModel() }
    viewModel { SecondAttemptPasswordViewModel() }
}

fun loadKoinModules() {
    org.koin.core.context.loadKoinModules(listOf(viewModelModule))
}
