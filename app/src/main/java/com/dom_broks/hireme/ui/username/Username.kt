package com.dom_broks.hireme.ui.username

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.dom_broks.hireme.R
import com.dom_broks.hireme.data.AuthListener
import com.dom_broks.hireme.databinding.ActivityUsernameBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class Username : AppCompatActivity(), AuthListener, KodeinAware {
    override val kodein by kodein()
    private val factory: UsernameViewModelFactory by instance<UsernameViewModelFactory>()
    private lateinit var viewModel: UsernameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username)

        viewModel = ViewModelProviders.of(this, factory)
            .get(UsernameViewModel::class.java)

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