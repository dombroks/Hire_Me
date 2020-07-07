package com.dom_broks.hireme.ui.welcomeScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.dom_broks.hireme.R
import com.dom_broks.hireme.databinding.ActivityWelcomeScreenBinding

class WelcomeScreen : AppCompatActivity() {
    private lateinit var viewModel: WelcomeScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        val binding : ActivityWelcomeScreenBinding = DataBindingUtil.setContentView(this,R.layout.activity_welcome_screen)
        viewModel=ViewModelProviders.of(this).get(WelcomeScreenViewModel::class.java)
        binding.viewmodel=viewModel
    }
}