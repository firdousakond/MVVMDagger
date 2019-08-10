package com.firdous.mvvmdagger.base

import androidx.lifecycle.ViewModel
import com.firdous.mvvmdagger.injection.component.DaggerViewModelInjector
import com.firdous.mvvmdagger.injection.component.ViewModelInjector
import com.firdous.mvvmdagger.injection.module.NetworkModule
import com.firdous.mvvmdagger.ui.post.PostListViewModel

abstract class BaseViewModel : ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}