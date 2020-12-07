package com.dom_broks.hireme.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dom_broks.hireme.data.Repository
import com.dom_broks.hireme.model.Job
import com.dom_broks.hireme.utils.DataHolder
import com.firebase.ui.auth.data.model.Resource
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    private val _jobs = MutableLiveData<com.dom_broks.hireme.utils.Resource<List<Job>>>()
    val jobs get() = _jobs

    fun getJobs() = viewModelScope.launch {
        repository.getJobs(object : DataHolder {
            override fun <T : Any> hold(data: T) {
                _jobs.postValue(data as com.dom_broks.hireme.utils.Resource<List<Job>>)
            }
        })
    }

}