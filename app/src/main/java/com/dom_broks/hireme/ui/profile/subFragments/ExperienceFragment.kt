package com.dom_broks.hireme.ui.profile.subFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dom_broks.hireme.R
import com.dom_broks.hireme.adapter.experienceDataAdapter
import com.dom_broks.hireme.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_experience.*

@AndroidEntryPoint
class ExperienceFragment : Fragment(R.layout.fragment_experience) {
    private val viewModel: ProfileViewModel by viewModels()

    override fun onStart() {
        viewModel.getUserExperience()
        super.onStart()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        AddExperience.setOnClickListener {
            AddItemDialog.newInstance("title","subtitle").show(childFragmentManager, AddItemDialog.TAG)
        }
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initRecyclerView() {
        experienceRecyclerView.apply {
            viewModel.experienceData.observe(
                viewLifecycleOwner,
                Observer { adapter = experienceDataAdapter(it) })
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(requireContext())
        }
    }




}
