package com.dom_broks.hireme.data

import android.net.Uri

import com.dom_broks.hireme.model.Experience
import com.dom_broks.hireme.model.Job
import com.dom_broks.hireme.model.PortfolioItem
import com.dom_broks.hireme.model.User
import com.dom_broks.hireme.utils.Resource
import com.dom_broks.hireme.utils.DataHolder
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class FirebaseSource {


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
        val ref = firebaseStorage.reference.child("$folder/img${firebaseAuth.currentUser?.uid}")
        val uploadTask = ref.putFile(filePath)
        uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
            if (!task.isSuccessful) {
                task.exception?.let { emitter.onError(it) }
            }
            return@Continuation ref.downloadUrl
        }).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                GlobalScope.launch {
                    addImageToDatabase(firebaseAuth.currentUser?.uid, downloadUri.toString())
                }


            } else {
                // some kind of errors here to handle
            }

        }.addOnFailureListener {
            // some kind of errors here to handle
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

    fun getUserData(holder: DataHolder) {
        var user: User?
        val ref = firebaseDatabase.getReference("Users")
        ref.child(currentUser()?.uid.toString()).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                holder.hold(
                    Resource.error(
                        "Error occurred when getting user data: $error.message",
                        null
                    )
                )
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User::class.java)
                holder.hold(Resource.success(user))
            }
        })
    }

    fun getUserExperience(holder: DataHolder) {
        holder.hold(Resource.loading(null))
        var experienceData: MutableList<Experience>
        val ref = firebaseDatabase.getReference("Experience").child(currentUser()!!.uid)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                experienceData = mutableListOf()
                for (child in snapshot.children) {
                    val value = child.getValue(Experience::class.java)
                    experienceData.add(value!!)
                }
                holder.hold(Resource.success(experienceData))
            }

            override fun onCancelled(error: DatabaseError) {
                holder.hold(
                    Resource.error(
                        "Error occurred when getting user experience: ${error.message}",
                        null
                    )
                )
            }
        })
    }

    fun addExperience(exp: Experience) {
        val ref = firebaseDatabase.getReference("Experience").child(currentUser()?.uid.toString())
        val key = ref.push().key
        exp.Id = key.toString()
        ref.child(key.toString()).setValue(exp.toMap())
    }

    fun deleteExperienceItem(itemId: String) {
        val ref = firebaseDatabase.getReference("Experience").child(currentUser()!!.uid)
        ref.child(itemId).removeValue()
    }

    fun getPortfolioItems(holder: DataHolder) {
        holder.hold(Resource.loading(null))
        var listOfItems: MutableList<PortfolioItem>?
        val ref = firebaseDatabase.getReference("Portfolio").child(currentUser()!!.uid)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                holder.hold(
                    Resource.error(
                        "Error occurred when fetching portfolio items: ${error.message} ",
                        null
                    )
                )
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                listOfItems = mutableListOf()
                for (child in snapshot.children) {
                    val item = child.getValue(PortfolioItem::class.java)
                    listOfItems!!.add(item!!)
                }
                holder.hold(Resource.success(listOfItems))
            }
        })
    }

    fun addPortfolioItem(item: PortfolioItem) {
        val ref = firebaseDatabase.getReference("Portfolio").child(currentUser()!!.uid)
        item.Id = ref.push().key
        ref.child(item.Id.toString()).setValue(item.toMap())
    }

    fun deletePortfolioItem(itemId: String) {
        val ref = firebaseDatabase.getReference("Portfolio").child(currentUser()!!.uid)
        ref.child(itemId).removeValue()
    }

    fun getJobs(holder: DataHolder) {
        holder.hold(Resource.loading(null))
        //addJob()
        var listOfItems: MutableList<Job>?
        val ref = firebaseDatabase.getReference("Jobs")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                holder.hold(
                    Resource.error(
                        "Error occurred when fetching portfolio items: ${error.message} ",
                        null
                    )
                )
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                listOfItems = mutableListOf()
                for (child in snapshot.children) {
                    val item = child.getValue(Job::class.java)
                    listOfItems!!.add(item!!)
                }
                holder.hold(Resource.success(listOfItems))
            }
        })
    }

    // This function is just for uploading some data to show it in the main fragment
    private fun addJob() {
        val job = Job(
            Id = "",
            Title = "Software Architect",
            Salary = "$ 300K per year",
            Image = "https://firebasestorage.googleapis.com/v0/b/hire-me-2568d.appspot.com/o/ExperienceImages%2Ffacebook.jpeg?alt=media&token=cda8e8b2-c5ed-49f1-a452-8c7ddea1ddfa",
            Location = com.dom_broks.hireme.model.LatLng(52.5200, 13.4050),
            Experience = "> 5 years",
            Type = "Full Time",
            Company = "Facebook",
            Description = "We are looking for someone who works with REST Api and web services in general"
        )
        val ref = firebaseDatabase.getReference("Jobs")
        val key = ref.push().key
        job.Id = key
        ref.child(key.toString()).setValue(job.toMap())
    }
}
