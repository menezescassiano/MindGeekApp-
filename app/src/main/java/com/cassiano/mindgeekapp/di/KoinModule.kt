package com.cassiano.mindgeekapp.di

import com.cassiano.mindgeekapp.home.view.viewmodel.MainViewModel
import com.cassiano.mindgeekapp.password.view.viewmodel.FirstAttemptPasswordViewModel
import com.cassiano.mindgeekapp.utils.ResourceManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val resourceManager = module {
    single { ResourceManager(context = get()) }
}

private val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { FirstAttemptPasswordViewModel() }
    //viewModel { RecipeDetailViewModel(resourceManager = get()) }
}

fun loadKoinModules() {
    org.koin.core.context.loadKoinModules(
        listOf(
            /*serviceModule,
            repositoryModule,*/
            resourceManager,
            viewModelModule
        )
    )
}