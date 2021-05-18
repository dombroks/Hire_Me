package com.dom_broks.hireme.ui.splashScreen

import android.content.Intent
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bolts.Task.delay
import com.dom_broks.hireme.data.Repository
import com.dom_broks.hireme.ui.auth.view.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel
@Inject
constructor
    (private val repository: Repository) : ViewModel() {


    val user by lazy { repository.currentUser() }
    val liveData: LiveData<SplashState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()

    init {
        GlobalScope.launch {
            kotlinx.coroutines.delay(5000)
            mutableLiveData.postValue(SplashState.SignUpActivity())

        }
    }


}

sealed class SplashState {
    class SignUpActivity : SplashState()

}