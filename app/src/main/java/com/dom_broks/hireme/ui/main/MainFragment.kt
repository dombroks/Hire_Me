package com.dom_broks.hireme.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dom_broks.hireme.R
import com.dom_broks.hireme.adapter.JobAdapter
import com.dom_broks.hireme.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mainAdapter: JobAdapter
    private val mainViewModel: MainViewModel by viewModels()
    private val viewModel2: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel2.getUserData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView() {

    }

}