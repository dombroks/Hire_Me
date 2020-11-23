package com.dom_broks.hireme.ui.username

import android.content.Intent
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.dom_broks.hireme.data.AuthListener
import com.dom_broks.hireme.data.Repository
import com.dom_broks.hireme.ui.auth.view.Login
import com.dom_broks.hireme.ui.home.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UsernameViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    var username: String? = null
    var authListener: AuthListener? = null
    private val disposables = CompositeDisposable()
    private val user by lazy { repository.currentUser() }


    private fun updateUsername() {
        val disposable =
            repository.updateUsername(user!!.uid, username!!.trim())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    authListener?.onSuccess()
                }, {
                    authListener?.onFailure(it.message!!)
                }
                )
        disposables.add(disposable)





    }
    fun goToHome(view: View) {
        updateUsername()
        Intent(view.context, MainActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

}