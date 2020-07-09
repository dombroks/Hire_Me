package com.dom_broks.hireme.ui.welcomeScreen

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.dom_broks.hireme.ui.auth.view.Register

class WelcomeScreenViewModel : ViewModel() {




    fun goToRegister(view: View) {
        Intent(view.context, Register::class.java).also {
            view.context.startActivity(it)
        }
    }
}