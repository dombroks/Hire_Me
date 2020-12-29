package com.dom_broks.hireme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.transition.Visibility
import androidx.transition.VisibilityPropagation
import com.dom_broks.hireme.model.Job

import com.dom_broks.hireme.utils.generateMapCameraLocation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_map.mapView
import kotlinx.android.synthetic.main.fragment_map_fragement.*


class ExpandedMapFragment() : Fragment(), OnMapReadyCallback {
    lateinit var googleMap: GoogleMap
    lateinit var item: Job


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        expandedMap.onCreate(savedInstanceState)
        expandedMap.onResume()
        expandedMap.getMapAsync(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expand_map_back_arrow.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("item", item)
            findNavController().navigate(
                R.id.jobDetailFragment,
                bundle
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        item = (arguments?.get("item") as Job?)!!

        return inflater.inflate(R.layout.fragment_map_fragement, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ExpandedMapFragment()
    }

    override fun onMapReady(map: GoogleMap?) {
        val location = LatLng(item.Location?.latitude!!, item.Location?.longitude!!)

        map?.let {
            googleMap = it
            googleMap.apply {
                moveCamera(CameraUpdateFactory.newLatLng(location))
                addMarker(
                    MarkerOptions()
                        .position(location)
                        .title("Work Location")
                )

            }
        }
    }
}