package com.dom_broks.hireme.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dom_broks.hireme.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

/*
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.profileFragment,
                R.id.chatFragment,
                R.id.mainFragment,
                R.id.notificationFragment
            )
        )
*/
        val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
       // setupActionBarWithNavController(navController, appBarConfiguration)


    }
}   