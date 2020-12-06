package com.dom_broks.hireme.ui.profile.subFragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var experienceDataAdapter: ExperienceDataAdapter


    private val itemCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteExperienceItem(experienceDataAdapter.getItemIdAt(position))
                experienceDataAdapter.notifyItemRemoved(position)
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getUserExperience()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        AddExperience.setOnClickListener {
            AddExperienceItemDialog.newInstance()
                .show(childFragmentManager, AddExperienceItemDialog.TAG)
        }
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initRecyclerView() {
        experienceRecyclerView.apply {
            viewModel.experienceData.observe(
                viewLifecycleOwner,
                Observer {
                    if (it.status == Status.SUCCESS) {
                        experienceDataAdapter = ExperienceDataAdapter(it.data!!)
                        adapter = experienceDataAdapter
                    } else
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                })
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(requireContext())
            val itemTouchHelper = ItemTouchHelper(itemCallback)
            itemTouchHelper.attachToRecyclerView(this)
        }
    }


}
