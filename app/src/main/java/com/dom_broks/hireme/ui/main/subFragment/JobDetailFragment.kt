package com.dom_broks.hireme.ui.main.subFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dom_broks.hireme.R


class JobDetailFragment : Fragment(R.layout.fragment_job_detail_fragement) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_detail_fragement, container, false)
    }

    companion object {
        fun newInstance() =
            JobDetailFragment()
    }
}