package com.dom_broks.hireme.ui.username

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.dom_broks.hireme.data.AuthListener
import com.dom_broks.hireme.data.Repository
import com.dom_broks.hireme.ui.home.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class UsernameViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

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