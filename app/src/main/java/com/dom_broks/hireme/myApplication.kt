package com.dom_broks.hireme

import android.app.Application
import com.dom_broks.hireme.data.FirebaseSource
import com.dom_broks.hireme.data.Repository
import com.dom_broks.hireme.ui.auth.viewModel.AuthViewModelFactory
import com.dom_broks.hireme.ui.profile.ProfileViewModelFactory
import com.dom_broks.hireme.ui.splashScreen.SplashScreenViewModelFactory
import com.dom_broks.hireme.ui.username.UsernameViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class myApplication:Application(),KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@myApplication))

        bind() from singleton { FirebaseSource() }
        bind() from singleton { Repository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { SplashScreenViewModelFactory(instance()) }
        bind() from provider { UsernameViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }

    }

}