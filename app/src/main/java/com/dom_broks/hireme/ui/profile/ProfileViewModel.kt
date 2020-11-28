package com.dom_broks.hireme.ui.profile

import android.net.Uri
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.dom_broks.hireme.data.AuthListener
import com.dom_broks.hireme.data.Repository
import com.dom_broks.hireme.model.Experience
import com.dom_broks.hireme.model.User
import com.dom_broks.hireme.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.Duration

class ProfileViewModel
@ViewModelInject
constructor(private val repository: Repository) : ViewModel() {

    val userData = MutableLiveData<Resource<User>>()

    private val _experienceData = MutableLiveData<List<Experience>>()
    val experienceData get() = _experienceData

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

    fun getUserExperience() {
        var list: List<Experience>? = null
        viewModelScope.launch {
            list = repository.getUserExperience()
        }
        _experienceData.value = list

    }

    fun getUserData() {
        viewModelScope.launch {
            userData.postValue(repository.getUserData().value)
        }
    }

    fun addExperience(title: String, place: String, from: String, to: String,duration: Long) {
        val exp = Experience(title, "$duration years", from, to, place)
        viewModelScope.launch {
            repository.addExperience(exp)
        }


    }


}