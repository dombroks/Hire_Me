package com.dom_broks.hireme.ui.profile.subFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dom_broks.hireme.R
import com.dom_broks.hireme.adapter.ExperienceDataAdapter
import com.dom_broks.hireme.ui.profile.ProfileViewModel
import com.dom_broks.hireme.ui.profile.subFragments.Dialog.AddExperienceItemDialog
import com.dom_broks.hireme.utils.Status
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
            AddExperienceItemDialog.newInstance().show(childFragmentManager, AddExperienceItemDialog.TAG)
        }
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initRecyclerView() {
        experienceRecyclerView.apply {
            viewModel.experienceData.observe(
                viewLifecycleOwner,
                Observer {
                    if (it.status == Status.SUCCESS) {
                        adapter = ExperienceDataAdapter(it.data!!)
                    } else
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                })
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(requireContext())
        }
    }


}
