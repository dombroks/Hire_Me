package com.dom_broks.hireme.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dom_broks.hireme.R
import com.dom_broks.hireme.adapter.JobAdapter
import com.dom_broks.hireme.adapter.PortfolioDataAdapter
import com.dom_broks.hireme.ui.profile.ProfileViewModel
import com.dom_broks.hireme.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_portfolio.*
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mainAdapter: JobAdapter
    private val mainViewModel: MainViewModel by viewModels()
    private val viewModel2: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel.getJobs()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        viewModel2.getUserData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView() {
        jobRv.apply {
            mainViewModel.jobs.observe(
                viewLifecycleOwner,
                Observer {
                    if (it.status == Status.SUCCESS) {
                        mainAdapter = JobAdapter(it.data!!)
                        adapter = mainAdapter
                    } else {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                })
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(requireContext())
        }

    }

}