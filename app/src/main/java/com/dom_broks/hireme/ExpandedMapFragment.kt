package com.dom_broks.hireme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dom_broks.hireme.model.Job
import com.dom_broks.hireme.ui.main.subFragment.MapFragment
import com.dom_broks.hireme.utils.generateMapCameraLocation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*


class ExpandedMapFragment() : Fragment(), OnMapReadyCallback {
    lateinit var googleMap: GoogleMap
    lateinit var item : Job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        item = (arguments?.get("item") as Job?)!!

        // Inflate the layout for this fragment
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
                this.moveCamera(CameraUpdateFactory.newLatLng(generateMapCameraLocation(location)))
                //this.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-33.100,151.100), 10f))
                this.addMarker(
                    MarkerOptions()
                        .position(location)
                        .title("Work Location")
                )

            }
        }
    }
}