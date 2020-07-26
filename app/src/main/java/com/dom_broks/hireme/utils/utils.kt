package com.dom_broks.hireme.utils

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dom_broks.hireme.ui.auth.view.Login
import com.dom_broks.hireme.ui.home.MainActivity
import com.dom_broks.hireme.ui.welcomeScreen.WelcomeScreen

fun Fragment.addChildFragment(fragment: Fragment,frameId:Int){
    val transaction=childFragmentManager.beginTransaction()
    transaction.replace(frameId,fragment).commit()
}


fun Context.startHomeActivity() =
    Intent(this, MainActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startLoginActivity() =
    Intent(this, Login::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
fun Context.startWelcomeActivity() =
    Intent(this, WelcomeScreen::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}