package com.dom_broks.hireme.ui.profile.subFragments.Dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.dom_broks.hireme.R
import com.dom_broks.hireme.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_portfolio_item_dialog.*
import kotlinx.android.synthetic.main.fragment_portfolio.*

@AndroidEntryPoint
class AddPortfolioItemDialog : DialogFragment() {
    private val viewModel: ProfileViewModel by viewModels()

    companion object {
        const val TAG = "AddPortfolioItemDialog"
        fun newInstance(): AddPortfolioItemDialog {
            return AddPortfolioItemDialog()
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
        return inflater.inflate(R.layout.add_portfolio_item_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addItem.setOnClickListener {
            viewModel.addPortfolioItem(title =
                projectName.text.toString(),
                image = ""
            )
            dialog?.dismiss()
        }
    }
}