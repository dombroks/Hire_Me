package com.dom_broks.hireme.ui.profile.subFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.dom_broks.hireme.DatePickerDialogFragment
import com.dom_broks.hireme.R
import kotlinx.android.synthetic.main.add_item_dialog.*

class AddItemDialog : DialogFragment() {

    companion object {

        const val TAG = "AddItemDialog"

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

        fun newInstance(title: String, subTitle: String): AddItemDialog {

            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
            val fragment = AddItemDialog()
            fragment.arguments = args
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
            val jobTitle: String = title.text.toString()
            val companyName: String = companyName.text.toString()

            Toast.makeText(context, jobTitle, Toast.LENGTH_LONG).show()

        }
        Begin.setOnClickListener {
            DatePickerDialogFragment.newInstance()
                .show(childFragmentManager, DatePickerDialogFragment.TAG)
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


}