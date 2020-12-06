package com.dom_broks.hireme.ui.profile.subFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dom_broks.hireme.R
import com.dom_broks.hireme.adapter.ExperienceDataAdapter
import com.dom_broks.hireme.adapter.PortfolioDataAdapter
import com.dom_broks.hireme.model.PortfolioItem
import com.dom_broks.hireme.ui.profile.ProfileViewModel
import com.dom_broks.hireme.ui.profile.subFragments.Dialog.AddPortfolioItemDialog
import com.dom_broks.hireme.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_experience.*
import kotlinx.android.synthetic.main.fragment_portfolio.*
import kotlinx.android.synthetic.main.portfolio_list_item.*

@AndroidEntryPoint
class PortfolioFragment : Fragment(R.layout.fragment_portfolio),PortfolioDataAdapter.OnItemClickListener {
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

        addPortfolioItem.setOnClickListener {
            AddPortfolioItemDialog.newInstance()
                .show(childFragmentManager, AddPortfolioItemDialog.TAG)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView() {
        portfolioRv.apply {
            viewModel.portfolioItems.observe(
                viewLifecycleOwner,
                Observer {
                    if (it.status == Status.SUCCESS)
                        adapter = PortfolioDataAdapter(it.data!!,this@PortfolioFragment)
                    else {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                })
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(context,"You have been clicked on the item $position",Toast.LENGTH_LONG).show()
    }

    override fun onItemDelete(item : PortfolioItem) {
        Toast.makeText(context,"You have been deleted the item ${item.Title}",Toast.LENGTH_LONG).show()
    }

}