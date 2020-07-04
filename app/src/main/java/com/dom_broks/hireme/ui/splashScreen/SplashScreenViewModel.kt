package com.dom_broks.hireme.ui.splashScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bolts.Task.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SplashScreenViewModel : ViewModel() {

    val liveData: LiveData<SplashState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()

    init {
        GlobalScope.launch{
            kotlinx.coroutines.delay(5000)
            mutableLiveData.postValue(SplashState.LoginActivity())

        }
    }

}
sealed class SplashState {
    class LoginActivity : SplashState()
}