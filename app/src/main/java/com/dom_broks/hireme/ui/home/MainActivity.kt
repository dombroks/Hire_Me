package com.dom_broks.hireme.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dom_broks.hireme.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)

        // Hide bottom navigation view in Expanded map fragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.expandedMapFragment -> bottomNavigationView.visibility = View.GONE
                R.id.jobDetailFragment -> bottomNavigationView.visibility = View.VISIBLE
            }
        }

    }
    private fun showOrHideBottomNavView(){

    }
}   