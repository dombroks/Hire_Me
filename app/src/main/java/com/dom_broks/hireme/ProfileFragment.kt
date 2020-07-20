package com.dom_broks.hireme

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel


    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.profile_fragment, container, false)


        val infoBtn: TextView = view.findViewById(R.id.infoBtn)
        val portfolioBtn: TextView = view.findViewById(R.id.portfolioBtn)
        val experienceBtn: TextView = view.findViewById(R.id.experienceBtn)
        portfolioBtn.setBackgroundResource(R.drawable.button_shape_two)
        val avatar: ImageView = view.findViewById(R.id.circleImageView)



        infoBtn.setOnClickListener(View.OnClickListener {
            changeToSelectedColor(infoBtn, portfolioBtn, experienceBtn)
        })
        experienceBtn.setOnClickListener(View.OnClickListener {
            changeToSelectedColor(experienceBtn, portfolioBtn, infoBtn)
        })
        portfolioBtn.setOnClickListener(View.OnClickListener {
            changeToSelectedColor(portfolioBtn, infoBtn, experienceBtn)
        })

        return view


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun changeToSelectedColor(view: View, view2: View, view3: View) {
        view.setBackgroundResource(R.drawable.button_shape_two)
        view2.setBackgroundResource(R.drawable.button_shape_three)
        view3.setBackgroundResource(R.drawable.button_shape_three)

    }

}