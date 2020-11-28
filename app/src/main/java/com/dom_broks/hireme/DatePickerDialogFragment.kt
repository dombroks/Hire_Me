package com.dom_broks.hireme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.dom_broks.hireme.ui.profile.ProfileViewModel
import com.dom_broks.hireme.utils.toMonth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.date_picker.*



@AndroidEntryPoint
class DatePickerDialogFragment : DialogFragment() {


    private val viewModel: ProfileViewModel by viewModels()

    companion object {
        const val TAG = "DatePickerDialogFragment"
        fun newInstance(): DatePickerDialogFragment {
            val fragment = DatePickerDialogFragment()
            //fragment.isCancelable = false
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.date_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key: String = arguments?.get("key") as String
        setDateBtn.setOnClickListener {
            Toast.makeText(context,key,Toast.LENGTH_LONG).show()
            val date: String =
                toMonth(datePicker2.month + 1) + " " + datePicker2.dayOfMonth + " " + datePicker2.year
            when (key.trim()) {
                "begin" -> viewModel.startingDate = date

                "end" -> viewModel.endDate = date
            }

            super.dismiss()
        }


    }
}