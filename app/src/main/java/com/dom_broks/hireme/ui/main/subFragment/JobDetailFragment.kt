package com.dom_broks.hireme.ui.main.subFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dom_broks.hireme.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_job_detail_fragement.*


class JobDetailFragment : Fragment(R.layout.fragment_job_detail_fragement) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.map,
            MapFragment.newInstance()
        )?.commit()

        return inflater.inflate(R.layout.fragment_job_detail_fragement, container, false)
    }

    companion object {
        fun newInstance() =
            JobDetailFragment()
    }


}