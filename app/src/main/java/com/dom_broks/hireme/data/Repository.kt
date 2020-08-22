package com.dom_broks.hireme.data

import android.net.Uri
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class Repository
@Inject
constructor(private val firebase: FirebaseSource) {

    fun login(email: String, password: String) =
        firebase.login(email, password)

    fun register(email: String, password: String) =
        firebase.register(email, password)

    fun currentUser() =
        firebase.currentUser()

    fun logout() = firebase.logout()

    fun loginWithGoogle(idToken: String) =
        firebase.loginWithGoogle(idToken)

    fun addNewUser(id: String, email: String, username: String, picture: String) =
        firebase.addNewUser(id, email, username, picture)

    fun addImageToDatabase(userUid: String?, uri: String) =
        firebase.addImageToDatabase(userUid, uri)

    fun addImageToStorage(filePath: Uri, folder: String) =
        firebase.addImageToStorage(filePath, folder)

    fun updateUsername(id: String, username: String) =
        firebase.updateUsername(id, username)

    suspend fun getUserExperience() =
        firebase.getUserExperience()

    fun dispose() =
        firebase.dispose()

}

