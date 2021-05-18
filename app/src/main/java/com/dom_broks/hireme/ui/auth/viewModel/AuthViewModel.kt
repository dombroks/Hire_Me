package com.dom_broks.hireme.ui.auth.viewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.dom_broks.hireme.data.AuthListener
import com.dom_broks.hireme.data.Repository
import com.dom_broks.hireme.ui.auth.view.Login
import com.dom_broks.hireme.ui.auth.view.Register
import com.dom_broks.hireme.ui.username.Username
import com.dom_broks.hireme.utils.isEmailValid
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class AuthViewModel
@Inject
constructor(private val repository: Repository) : ViewModel() {
    var email: String? = null
    var password: String? = null

    var passwordConfirm: String? = null


    var authListener: AuthListener? = null
    val user by lazy { repository.currentUser() }
    private val disposables = CompositeDisposable()

    private fun saveUser() {
        val disposable =
            repository.addNewUser(
                repository.currentUser()!!.uid,
                email!!,
                "nothing right now",
                "nothing right now"
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        authListener?.onSuccess()
                    }, {
                        authListener?.onFailure(it.message!!)
                    }
                )
        disposables.add(disposable)

    }

    fun loginWithGoogle(idToken: String) {
        val disposable = repository.loginWithGoogle(idToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("message", "the operation was completed")
                authListener?.onSuccess()
            }, {

                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)


    }


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
        if (email.isNullOrEmpty() || password.isNullOrEmpty() || passwordConfirm.isNullOrEmpty()) {
            authListener?.onFailure("Please add valid credentials")


        } else {
            if (!password.equals(passwordConfirm)) {
                authListener?.onFailure("Passwords are not matches")
            } else if (!isEmailValid(email!!)) {
                authListener?.onFailure("Please add a valid email")

            } else {

                authListener?.onStarted()

                val disposable = repository.register(email!!, password!!)
                    .observeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        authListener?.onSuccess()
                        saveUser()

                    }, {
                        Log.e("message", "signUp:${it.message!!} ")
                        authListener?.onFailure(it.message!!)
                    })
                disposables.add(disposable)
            }
        }
    }

    fun goToSignUp(view: View) {
        Intent(view.context, Register::class.java).also {
            view.context.startActivity(it)
        }

    }

    fun goToLogin(view: View) {
        Intent(view.context, Login::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun goToUsernameActivity(ctx: Context) {
        Intent(ctx, Username::class.java).also {
            ctx.startActivity(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}