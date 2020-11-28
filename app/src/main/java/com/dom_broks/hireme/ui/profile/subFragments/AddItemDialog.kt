package com.dom_broks.hireme.ui.profile.subFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.dom_broks.hireme.DatePickerDialogFragment
import com.dom_broks.hireme.R
import com.dom_broks.hireme.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_item_dialog.*

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
            val jobTitle: String = title.text.toString()
            val companyName: String = companyName.text.toString()
            viewModel.addExperience(jobTitle, companyName)

            Toast.makeText(context, jobTitle, Toast.LENGTH_LONG).show()

        }
        Begin.setOnClickListener {
            val dpdFragment = DatePickerDialogFragment.newInstance()
            val bundle = Bundle()
            bundle.putString("key", "begin")
            dpdFragment.arguments = bundle
            dpdFragment
                .show(childFragmentManager, DatePickerDialogFragment.TAG)
        }
        End.setOnClickListener {
            val dpdFragment = DatePickerDialogFragment.newInstance()
            val bundle = Bundle()
            bundle.putString("key", "end")
            dpdFragment.arguments = bundle
            dpdFragment
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