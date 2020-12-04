package com.dom_broks.hireme.ui.profile.subFragments.Dialog

import android.os.Bundle
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
import kotlinx.android.synthetic.main.add_experience_item_dialog.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddExperienceItemDialog : DialogFragment() {
    private val viewModel: ProfileViewModel by viewModels()

    companion object {
        const val TAG = "AddExperienceItemDialog"
        fun newInstance(): AddExperienceItemDialog {
            val fragment =
                AddExperienceItemDialog()
            return fragment
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.add_experience_item_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addBtn.setOnClickListener {
            val jobTitle = title.text.toString()
            val companyName = companyName.text.toString()
            val from = Begin.text.toString()
            val to = End.text.toString()

            if (!isValidDate(from) || !isValidDate(to)) {
                Toast.makeText(context, "Please write a valid date", Toast.LENGTH_LONG).show()
            } else {
                viewModel.addExperience(jobTitle, companyName, from, to, getDuration(from, to))
                super.dismiss()
            }
        }
    }

    private fun changeToSelectedColor(view: View, view2: View) {
        view.setBackgroundResource(R.drawable.button_shape_two)
        view2.setBackgroundResource(R.drawable.button_shape_three)
    }


    private fun isValidDate(date: String): Boolean {
        var isValid = true
        try {
            var formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val parsedDate = formatter.parse(date)

        } catch (e: Exception) {
            isValid = false
        }

        return isValid
    }

    private fun getDuration(date1: String, date2: String): Long {
        var formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val parsedDate1 = formatter.parse(date1)
        val parsedDate2 = formatter.parse(date2)
        var diff: Long = parsedDate2.time - parsedDate1.time
        diff = diff / 1000 / 60 / 60 / 24 / 30 / 12
        return if (diff != 0L) diff else diff * 12
    }
}