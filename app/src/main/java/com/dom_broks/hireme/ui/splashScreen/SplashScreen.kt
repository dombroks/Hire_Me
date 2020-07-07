package com.dom_broks.hireme.ui.splashScreen

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dom_broks.hireme.R
import com.dom_broks.hireme.ui.auth.viewModel.AuthViewModelFactory
import com.dom_broks.hireme.utils.startHomeActivity
import com.dom_broks.hireme.utils.startLoginActivity
import com.dom_broks.hireme.utils.startWelcomeActivity
import io.reactivex.internal.operators.maybe.MaybeToPublisher.instance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SplashScreen : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    lateinit var viewModel: SplashScreenViewModel

    private val factory: SplashScreenViewModelFactory by instance<SplashScreenViewModelFactory>()


    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        setupViewModel()


    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProviders.of(this, factory)
                .get(SplashScreenViewModel::class.java)


        viewModel.liveData.observe(this, Observer {

            when (it) {
                is SplashState.SignUpActivity -> {
                    finish()
                    startWelcomeActivity()
                }
            }

        })

    }

    override fun onStart() {
        super.onStart()

        viewModel.user?.let {
            startHomeActivity()

        }
    }


}
