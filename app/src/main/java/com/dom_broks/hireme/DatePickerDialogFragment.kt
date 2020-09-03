package com.dom_broks.hireme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.DialogFragment
import com.dom_broks.hireme.utils.toMonth
import kotlinx.android.synthetic.main.date_picker.*

class DatePickerDialogFragment : DialogFragment() {
    companion object {
        const val TAG = "DatePickerDialogFragment"
        fun newInstance(): DatePickerDialogFragment {
            return DatePickerDialogFragment()
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
        setDateBtn.setOnClickListener {
            indice.text = "Ending"
            presentCheckBox.visibility = View.VISIBLE
            setDateBtn.text = " Set Ending Date "
            Toast.makeText(requireContext(), toMonth(datePicker2.month + 1), LENGTH_LONG).show()


        }


    }
}