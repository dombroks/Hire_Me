package com.dom_broks.hireme.data

import android.net.Uri
import com.dom_broks.hireme.data.FirebaseSource

class Repository(private val firebase: FirebaseSource) {

    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()

    fun loginWithGoogle(idToken: String) = firebase.loginWithGoogle(idToken)

    fun addNewUser(id: String, name: String, picture: String) =
        firebase.addNewUser(id, name, picture)

    fun addImageToDatabase(userUid: String?, uri: String) =
        firebase.addImageToDatabase(userUid, uri)

    fun addImageToStorage(filePath: Uri, folder: String) =
        firebase.addImageToStorage(filePath, folder)

}

