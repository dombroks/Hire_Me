package com.dom_broks.hireme.ui.profile.subFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dom_broks.hireme.R
import com.dom_broks.hireme.adapter.ExperienceDataAdapter
import com.dom_broks.hireme.adapter.PortfolioDataAdapter
import com.dom_broks.hireme.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_experience.*
import kotlinx.android.synthetic.main.fragment_portfolio.*

@AndroidEntryPoint
class PortfolioFragment : Fragment(R.layout.fragment_portfolio) {
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.fetchPortfolioItems()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        super.onViewCreated(view, savedInstanceState)


    }

    private fun initRecyclerView() {

        portfolioRv.apply {
            viewModel.portfolioItems.observe(
                viewLifecycleOwner,
                Observer {adapter = PortfolioDataAdapter(it.data!!) })
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(requireContext())

        }
    }

}