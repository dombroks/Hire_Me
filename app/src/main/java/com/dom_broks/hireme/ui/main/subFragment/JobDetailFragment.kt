package com.dom_broks.hireme.ui.main.subFragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dom_broks.hireme.R
import com.dom_broks.hireme.model.Job
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_job_detail_fragement.*


class JobDetailFragment : Fragment(R.layout.fragment_job_detail_fragement) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item: Job? = arguments?.get("item") as Job?

        title.text = item?.Title!!.toString()
        company.text = item.Company!!.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.map,
            MapFragment.newInstance()
        )?.addToBackStack(null)
            ?.commit()



        return inflater.inflate(R.layout.fragment_job_detail_fragement, container, false)
    }

    companion object {
        fun newInstance() =
            JobDetailFragment()
    }


}