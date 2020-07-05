package com.dom_broks.hireme.ui.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dom_broks.hireme.data.Repository

@Suppress("UNCHECKED_CAST")
class SplashScreenViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
     return SplashScreenViewModel(repository) as T
    }
}