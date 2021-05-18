package com.dom_broks.hireme.ui.auth.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View

import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.dom_broks.hireme.R
import com.dom_broks.hireme.data.AuthListener
import com.dom_broks.hireme.databinding.ActivityLoginBinding
import com.dom_broks.hireme.databinding.ActivitySignUpBinding
import com.dom_broks.hireme.ui.auth.viewModel.AuthViewModel
import com.dom_broks.hireme.utils.startHomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class Login : AppCompatActivity(), AuthListener {


    private val viewModel: AuthViewModel by viewModels()



    //Sign with google
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 123


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.viewmodel = viewModel


        viewModel.authListener = this

        google.setOnClickListener(View.OnClickListener { signIn() })

        createGoogleSignInRequest()


    }

    private fun createGoogleSignInRequest() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.e("id token is", ": ${account.idToken}")

                //passing the id token to viewModel login with google function
                viewModel.loginWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.e("Failure", "Msg ${e.message}")
            }
        }
    }


    override fun onStarted() {

    }

    override fun onSuccess() {
        startHomeActivity()
    }

    override fun onFailure(message: String) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()

        viewModel.user?.let {
            startHomeActivity()

        }
    }


}


