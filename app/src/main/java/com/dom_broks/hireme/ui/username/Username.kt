package com.dom_broks.hireme.ui.username

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.dom_broks.hireme.R
import com.dom_broks.hireme.data.AuthListener
import com.dom_broks.hireme.databinding.ActivityUsernameBinding
import dagger.hilt.android.AndroidEntryPoint
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

@AndroidEntryPoint
class Username : AppCompatActivity(), AuthListener{


    private val viewModel: UsernameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username)


        val binding: ActivityUsernameBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_username)
        binding.viewmodel = viewModel

        viewModel.authListener = this


    }

    override fun onStarted() {
        TODO("Not yet implemented")
    }

    override fun onSuccess() {
        TODO("Not yet implemented")
    }

    override fun onFailure(message: String) {
        TODO("Not yet implemented")
    }
}