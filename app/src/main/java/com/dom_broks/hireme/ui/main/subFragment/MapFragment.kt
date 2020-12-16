package com.dom_broks.hireme.ui.main.subFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dom_broks.hireme.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        mapView.getMapAsync(this)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MapFragment()
    }

    override fun onMapReady(map: GoogleMap?) {
        val sydney = LatLng(-33.852, 151.211)

        map?.let {
            googleMap = it
            googleMap.apply {
                this.moveCamera(CameraUpdateFactory.newLatLng(sydney))
                this.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f))
                this.addMarker(
                    MarkerOptions()
                        .position(sydney)
                        .title("Work Location")
                )
            }
        }
    }
}
