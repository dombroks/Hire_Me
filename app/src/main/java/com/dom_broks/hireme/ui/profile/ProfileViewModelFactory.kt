package com.dom_broks.hireme.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dom_broks.hireme.data.Repository
import com.dom_broks.hireme.ui.auth.viewModel.AuthViewModel

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(
    private val repository: Repository
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }


}