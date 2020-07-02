package com.dom_broks.hireme.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dom_broks.hireme.R
import com.dom_broks.hireme.ui.auth.Login
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button2.setOnClickListener(View.OnClickListener {
            intent = Intent(this, Login::class.java).also { startActivity(it) } })
    }
}   