package com.dom_broks.hireme.ui.splashScreen

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dom_broks.hireme.R
import com.dom_broks.hireme.utils.startHomeActivity
import com.dom_broks.hireme.utils.startLoginActivity

class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val viewModel: SplashScreenViewModel = ViewModelProviders.of(this)
            .get(SplashScreenViewModel::class.java)
        viewModel.liveData.observe(this, Observer {
            when (it) {
                is SplashState.LoginActivity -> {
                    finish()
                    startLoginActivity()
                }
            }

        })


    }


}
