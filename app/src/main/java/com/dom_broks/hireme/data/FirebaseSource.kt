package com.dom_broks.hireme.data

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dom_broks.hireme.model.Experience
import com.dom_broks.hireme.model.User
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import io.reactivex.Completable


class FirebaseSource {
    private val experienceData = mutableListOf<Experience>()
    private val imageUri = MutableLiveData<String>()


    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val firebaseDatabase: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }
    private val firebaseStorage: FirebaseStorage by lazy {
        FirebaseStorage.getInstance()
    }


    fun addImageToStorage(filePath: Uri, folder: String) = Completable.create { emitter ->
        if (filePath != null) {
            val ref = firebaseStorage.reference.child("$folder/img${firebaseAuth.currentUser?.uid}")
            val uploadTask = ref.putFile(filePath)
            val urlTask =
                uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let { emitter.onError(it) }
                    }
                    return@Continuation ref.downloadUrl
                }).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result
                        addImageToDatabase(firebaseAuth.currentUser?.uid, downloadUri.toString())
                    } else {
                        // some kind of errors here to handle
                    }

                }.addOnFailureListener {
                    // some kind of errors here to handle
                }


        }
    }

    fun addImageToDatabase(userUid: String?, uri: String) {
        val ref = firebaseDatabase.getReference("Users")
        ref.child(userUid!!).child("picture").setValue(uri)
    }


    fun addNewUser(id: String, email: String, username: String, picture: String) =
        Completable.create { emitter ->
            val ref = firebaseDatabase.getReference("Users")
            val user = User(id, email, username, picture)
            if (!emitter.isDisposed) {
                ref.child(id).setValue(user)
                emitter.onComplete()
            }
        }

    fun updateUsername(id: String, username: String) = Completable.create { emitter ->
        val ref = firebaseDatabase.getReference("Users")
        if (!emitter.isDisposed) {
            ref.child(id).child("username").setValue(username)
            emitter.onComplete()
        }
    }


    fun loginWithGoogle(idToken: String) = Completable.create { emitter ->
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }


    fun login(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser

    fun getUserExperience(): List<Experience> {

        val ref = firebaseDatabase.getReference("Experience").child(currentUser()!!.uid)
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (child in snapshot.children) {
                        val value = child.getValue(Experience::class.java)
                        if (!experienceData.contains(value))
                            experienceData.add(value!!)
                    }
                } else {
                    Log.e("message", "no snapshots found")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Some error happened ${error.message}")
            }
        })

        return experienceData
    }

    fun dispose() {
        experienceData.clear()
    }

    suspend fun loadUserProfileImage() {
        val ref = firebaseDatabase.getReference("Users")
        ref.child(currentUser()?.uid.toString()).child("Image")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value as String
                    imageUri.value = value
                }

            })

    }


}
