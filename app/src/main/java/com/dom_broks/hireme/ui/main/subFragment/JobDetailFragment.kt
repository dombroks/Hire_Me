package com.dom_broks.hireme.ui.main.subFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dom_broks.hireme.ExpandedMapFragment
import com.dom_broks.hireme.R
import com.dom_broks.hireme.model.Job
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_job_detail_fragement.*
import kotlinx.android.synthetic.main.main_fragment.*


class JobDetailFragment : Fragment(R.layout.fragment_job_detail_fragement) {
    private lateinit var item: Job

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()



        expand_btn.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("item", item)
            findNavController().navigate(
                R.id.expandedMapFragment,
                bundle
            )
        }
        back_arrow.setOnClickListener {
            findNavController().navigate(
                R.id.mainFragment
            )
        }

    }

    private fun setUpViews() {
        title.text = item.Title!!
        company.text = item.Company!!
        description.text = item.Description
        salary.text = item.Salary
        experience.text = item.Experience
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        item = (arguments?.get("item") as Job?)!!
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.map,
            MapFragment.newInstance(LatLng(item.Location?.latitude!!, item.Location?.longitude!!))
        )?.addToBackStack(null)
            ?.commit()
        return inflater.inflate(R.layout.fragment_job_detail_fragement, container, false)
    }

    companion object {
        fun newInstance() =
            JobDetailFragment()
    }


}