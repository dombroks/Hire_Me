package com.dom_broks.hireme.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dom_broks.hireme.R
import com.dom_broks.hireme.data.AuthListener
import com.dom_broks.hireme.databinding.ActivitySignUpBinding
import com.dom_broks.hireme.ui.auth.viewModel.AuthViewModel
import com.dom_broks.hireme.ui.auth.viewModel.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUp : AppCompatActivity() , AuthListener,KodeinAware{
    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance<AuthViewModelFactory>()
    private lateinit var viewModel: AuthViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val binding : ActivitySignUpBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        viewModel= ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel=viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
    }

    override fun onSuccess() {
    }

    override fun onFailure(message: String) {
    }


}