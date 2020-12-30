package com.dom_broks.hireme.data

import android.net.Uri
import com.dom_broks.hireme.model.Experience
import com.dom_broks.hireme.model.PortfolioItem
import com.dom_broks.hireme.utils.DataHolder
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

    fun addImageToDatabase(uri: String) =
        firebase.addImageToDatabase(currentUser()?.uid, uri)

    fun addImageToStorage(filePath: Uri, folder: String) =
        firebase.addImageToStorage(filePath, folder)

    fun updateUsername(id: String, username: String) =
        firebase.updateUsername(id, username)

    fun getUserExperience(holder: DataHolder) =
        firebase.getUserExperience(holder)

    fun getUserData(holder: DataHolder) =
        firebase.getUserData(holder)

    fun addExperience(exp: Experience) =
        firebase.addExperience(exp)

    fun deleteExperienceItem(itemId: String)=
        firebase.deleteExperienceItem(itemId)

    fun getPortfolioItems(holder: DataHolder) =
        firebase.getPortfolioItems(holder)

    fun addPortfolioItem(item: PortfolioItem) =
        firebase.addPortfolioItem(item)

    fun deletePortfolioItem(itemId: String)=
        firebase.deletePortfolioItem(itemId)

    fun getJobs(holder: DataHolder) =
        firebase.getJobs(holder)
}

