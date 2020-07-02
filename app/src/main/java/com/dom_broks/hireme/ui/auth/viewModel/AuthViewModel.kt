package com.dom_broks.hireme.ui.auth.viewModel

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.dom_broks.hireme.data.AuthListener
import com.dom_broks.hireme.data.Repository
import com.dom_broks.hireme.ui.auth.Login
import com.dom_broks.hireme.ui.auth.SignUp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel(private val repository: Repository) : ViewModel() {
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null
    val user by lazy { repository.currentUser() }
    private val disposables = CompositeDisposable()


    fun login() {

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("invalid Email Or Password")
        } else {
            authListener?.onStarted()


            val disposable = repository.login(email!!, password!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    authListener?.onSuccess()
                }, {

                    authListener?.onFailure(it.message!!)
                })
            disposables.add(disposable)

        }
    }

    fun signUp() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Please add valid credentials")
        } else {
            authListener?.onStarted()
            val disposable = repository.register(email!!, password!!)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    authListener?.onSuccess()
                }, {
                    authListener?.onFailure(it.message!!)
                })
            disposables.add(disposable)
        }
    }

    fun goToSignUp(view: View) {
        Intent(view.context, SignUp::class.java).also {
            view.context.startActivity(it)
        }

    }

    fun goToLogin(view: View) {
        Intent(view.context, Login::class.java).also {
            view.context.startActivity(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}