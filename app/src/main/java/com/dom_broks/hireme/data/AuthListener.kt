package com.dom_broks.hireme.data

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}

