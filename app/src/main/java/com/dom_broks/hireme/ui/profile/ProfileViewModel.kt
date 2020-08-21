package com.dom_broks.hireme.ui.profile

import android.net.Uri
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dom_broks.hireme.data.AuthListener
import com.dom_broks.hireme.data.Repository
import com.dom_broks.hireme.model.Experience
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ProfileViewModel
@ViewModelInject
constructor(private val repository: Repository) : ViewModel() {


    var authListener: AuthListener? = null
    private val disposables = CompositeDisposable()

    fun uploadPictureToFirebaseStorage(uri: Uri?, folder: String) {
        val disposable = repository.addImageToStorage(uri!!, folder)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)


    }

    fun getUserExperience(): List<Experience> {
        var list: List<Experience>? = null
        viewModelScope.launch {
            list = repository.getUserExperience()
        }
        return list!!
    }
}