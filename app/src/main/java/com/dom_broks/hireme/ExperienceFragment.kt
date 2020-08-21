package com.dom_broks.hireme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.hilt.lifecycle.ViewModelInject
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dom_broks.hireme.adapter.experienceDataAdapter
import com.dom_broks.hireme.data.Repository
import com.dom_broks.hireme.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_experience.*

@AndroidEntryPoint
class ExperienceFragment : Fragment(R.layout.fragment_experience) {


    private val viewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_experience, container, false)
    }

    companion object {
        fun newInstance() =
            ExperienceFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

    }

    private fun initRecyclerView() {

        experienceRecyclerView.apply {
            this.adapter = experienceDataAdapter(viewModel.getUserExperience())
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(requireContext())
        }

    }
}
