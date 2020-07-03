package com.dom_broks.hireme.data

import com.dom_broks.hireme.data.FirebaseSource

class Repository(private val firebase: FirebaseSource) {

    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()

    fun loginWithGoogle(idToken : String)=firebase.loginWithGoogle(idToken)


}

