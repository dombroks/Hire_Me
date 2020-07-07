package com.dom_broks.hireme.ui.welcomeScreen

import android.content.Intent
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.dom_broks.hireme.R
import com.dom_broks.hireme.databinding.ActivityWelcomeScreenBinding
import com.dom_broks.hireme.ui.auth.view.Login
import com.dom_broks.hireme.ui.auth.view.SignUp

class WelcomeScreenViewModel : ViewModel() {




    fun goToRegister(view: View) {
        Intent(view.context, SignUp::class.java).also {
            view.context.startActivity(it)
        }
    }
}