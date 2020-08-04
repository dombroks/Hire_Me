package com.dom_broks.hireme.ui.auth.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.dom_broks.hireme.R
import com.dom_broks.hireme.data.AuthListener
import com.dom_broks.hireme.databinding.ActivitySignUpBinding
import com.dom_broks.hireme.ui.auth.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Register : AppCompatActivity(), AuthListener {


    private val viewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val binding: ActivitySignUpBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        binding.viewmodel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
    }

    override fun onSuccess() {
        viewModel.goToUsernameActivity(this)
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}