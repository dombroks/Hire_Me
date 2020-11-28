package com.dom_broks.hireme.ui.profile.subFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.dom_broks.hireme.R
import com.dom_broks.hireme.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_item_dialog.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddItemDialog : DialogFragment() {
    private val viewModel: ProfileViewModel by viewModels()

    companion object {
        const val TAG = "AddItemDialog"
        fun newInstance(): AddItemDialog {
            val fragment = AddItemDialog()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.add_item_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addBtn.setOnClickListener {
            val jobTitle = title.text.toString()
            val companyName = companyName.text.toString()
            var from = Begin.text.toString()
            var to = End.text.toString()

            if (!isValidDate(from) || !isValidDate(to)) {
                Toast.makeText(context, "Please write a valid date", Toast.LENGTH_LONG).show()
            } else {
                viewModel.addExperience(jobTitle, companyName, from, to)
                super.dismiss()
            }


        }
    }

    private fun changeToSelectedColor(view: View, view2: View) {
        view.setBackgroundResource(R.drawable.button_shape_two)
        view2.setBackgroundResource(R.drawable.button_shape_three)

    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    fun isValidDate(date: String): Boolean {
        var isValid: Boolean = true
        try {
            var formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = formatter.parse(date)
            println(date)
        } catch (e: Exception) {
            isValid = false
        }

        return isValid
    }
}