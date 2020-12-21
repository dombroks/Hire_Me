package com.dom_broks.hireme.utils

import android.content.Context
import android.content.Intent
import android.location.Geocoder
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dom_broks.hireme.ui.auth.view.Login
import com.dom_broks.hireme.ui.home.MainActivity
import com.dom_broks.hireme.ui.welcomeScreen.WelcomeScreen
import com.google.android.gms.maps.model.LatLng

fun Fragment.addChildFragment(fragment: Fragment, frameId: Int) {
    val transaction = childFragmentManager.beginTransaction()
    transaction.replace(frameId, fragment).commit()
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

fun toMonth(month: Int): String {
    return when (month) {
        1 -> "Jan"
        2 -> "Feb"
        3 -> "Mar"
        4 -> "Apr"
        5 -> "May"
        6 -> "Jun"
        7 -> "Jul"
        8 -> "Aug"
        9 -> "Sep"
        10 -> "Oct"
        11 -> "Nov"
        12 -> "Dec"
        else -> "Null"
    }

}

fun generateMapCameraLocation(latLng: LatLng): LatLng {
    return LatLng(latLng.latitude - 50, latLng.longitude )
}

