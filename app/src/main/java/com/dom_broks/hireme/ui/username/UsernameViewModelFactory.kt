package com.dom_broks.hireme.ui.username

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dom_broks.hireme.data.Repository

class UsernameViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {


        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UsernameViewModel(repository) as T
        }
}