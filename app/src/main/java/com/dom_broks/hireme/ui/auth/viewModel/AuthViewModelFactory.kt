package com.dom_broks.hireme.ui.auth.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dom_broks.hireme.data.Repository



@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }

}