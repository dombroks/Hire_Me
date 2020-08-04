package com.dom_broks.hireme.ui.home

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.dom_broks.hireme.data.Repository
import com.dom_broks.hireme.utils.startLoginActivity

class MainActivityViewModel @ViewModelInject constructor(

    private val repository: Repository
) : ViewModel() {

    val user by lazy {
        repository.currentUser()
    }

    fun logout(view: View) {
        repository.logout()
        view.context.startLoginActivity()
    }
}
