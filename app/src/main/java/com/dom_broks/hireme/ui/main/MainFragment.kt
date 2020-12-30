package com.dom_broks.hireme.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dom_broks.hireme.R
import com.dom_broks.hireme.adapter.JobAdapter
import com.dom_broks.hireme.model.Job
import com.dom_broks.hireme.ui.profile.ProfileViewModel
import com.dom_broks.hireme.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import kotlinx.coroutines.Dispatchers
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment), JobAdapter.OnItemClickListener {


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mainAdapter: JobAdapter
    private lateinit var items: List<Job>
    private val mainViewModel: MainViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel.getJobs()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBar = view.findViewById(R.id.progressBar)
        initRecyclerView()
        profileViewModel.getUserData()
        getImage()
        searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }


            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        super.onViewCreated(view, savedInstanceState)
    }

    private fun filter(text: String) {
        val filteredList = ArrayList<Job>()
        for (job in items) {
            if (job.Title?.toLowerCase(Locale.ROOT)?.contains(text.toLowerCase(Locale.ROOT))!!) {
                filteredList.add(job)
            }
        }
        mainAdapter.filter(filteredList)
    }

    private fun initRecyclerView() {
        jobRv.apply {
            mainViewModel.jobs.observe(
                viewLifecycleOwner,
                Observer {
                    if (it.status == Status.LOADING) {
                        this@MainFragment.progressBar.visibility = View.VISIBLE
                    }
                    if (it.status == Status.SUCCESS) {
                        this@MainFragment.progressBar.visibility = View.GONE
                        items = it.data!!
                        mainAdapter = JobAdapter(it.data!!, this@MainFragment, context)
                        adapter = mainAdapter

                    } else if (it.status == Status.ERROR) {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                })
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun getImage() {
        profileViewModel.userData.observe(viewLifecycleOwner, Observer {
            val url = it.data?.picture
            Glide.with(this)
                .load(url)
                .circleCrop()
                .into(userImage)
        })
    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("item", items[position])
        findNavController().navigate(
            R.id.jobDetailFragment,
            bundle
        )
    }


}