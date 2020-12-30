package com.dom_broks.hireme.ui.profile.subFragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dom_broks.hireme.R
import com.dom_broks.hireme.adapter.PortfolioDataAdapter
import com.dom_broks.hireme.model.PortfolioItem
import com.dom_broks.hireme.ui.profile.ProfileViewModel
import com.dom_broks.hireme.ui.profile.subFragments.Dialog.AddPortfolioItemDialog
import com.dom_broks.hireme.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_portfolio.*
import kotlinx.android.synthetic.main.profile_fragment.*

@AndroidEntryPoint
class PortfolioFragment : Fragment(R.layout.fragment_portfolio),
    PortfolioDataAdapter.OnItemClickListener {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var portfolioAdapter: PortfolioDataAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.fetchPortfolioItems()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBar = view.findViewById(R.id.portfolio_progress_bar)
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
                    if (it.status == Status.LOADING) {
                            this@PortfolioFragment.progressBar.visibility = View.VISIBLE
                    }
                    if (it.status == Status.SUCCESS) {
                        this@PortfolioFragment.progressBar.visibility = View.GONE
                        portfolioAdapter = PortfolioDataAdapter(it.data!!, this@PortfolioFragment)
                        adapter = portfolioAdapter
                    } else if (it.status == Status.ERROR) {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                })
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(context, "You have been clicked on the item $position", Toast.LENGTH_LONG)
            .show()
    }

    override fun onItemDelete(item: PortfolioItem, position: Int) {
        viewModel.deletePortfolioItem(item.Id.toString())

        //Trying to use work manager to achieve the job
        /*
        val workManager = WorkManager.getInstance(requireContext())
        workManager.enqueue(OneTimeWorkRequest.from(DeleteWorker::class.java))
        Snackbar.make(portfolioLayout, "${item.Title} has been deleted", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                portfolioLayout.setBackgroundColor(Color.parseColor("#f2f2f2"))
            }
            .show()

         */
    }

}