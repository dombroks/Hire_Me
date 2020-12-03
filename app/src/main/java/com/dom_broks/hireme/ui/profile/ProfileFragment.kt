package com.dom_broks.hireme.ui.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.dom_broks.hireme.ui.profile.subFragments.ExperienceFragment
import com.dom_broks.hireme.ui.profile.subFragments.InfoFragment
import com.dom_broks.hireme.ui.profile.subFragments.PortfolioFragment
import com.dom_broks.hireme.R
import com.dom_broks.hireme.utils.Status
import com.dom_broks.hireme.utils.addChildFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.profile_fragment.*
import java.io.IOException
import java.lang.Exception


@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.profile_fragment) {
    private val INFO_FRAG_TAG = "fragment_info"
    private val PORTFOLIO_FRAG_TAG = "fragment_portfolio"
    private val EXPERIENCE_FRAG_TAG = "fragment_experience"


    private val viewModel: ProfileViewModel by viewModels()

    companion object {
        fun newInstance() = ProfileFragment()
    }


    private val PICK_IMAGE_REQUEST = 1
    private var filePath: Uri? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoFragment =
            InfoFragment()
        val portfolioFragment =
            PortfolioFragment()
        val experienceFragment =
            ExperienceFragment()



        addChildFragment(experienceFragment, R.id.child_fragments_container)

        circleImageView.setOnClickListener {
            launchGallery()
        }
    }


    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.profile_fragment, container, false)

        setUserData()

        val infoBtn: TextView = view.findViewById(R.id.infoBtn)
        val portfolioBtn: TextView = view.findViewById(R.id.portfolioBtn)
        val experienceBtn: TextView = view.findViewById(R.id.experienceBtn)
        experienceBtn.setBackgroundResource(R.drawable.button_shape_two)



        infoBtn.setOnClickListener {
            changeToSelectedColor(infoBtn, portfolioBtn, experienceBtn)
            replaceFragment(INFO_FRAG_TAG)
        }
        experienceBtn.setOnClickListener {
            changeToSelectedColor(experienceBtn, portfolioBtn, infoBtn)
            replaceFragment(EXPERIENCE_FRAG_TAG)
        }
        portfolioBtn.setOnClickListener {
            changeToSelectedColor(portfolioBtn, infoBtn, experienceBtn)
            replaceFragment(PORTFOLIO_FRAG_TAG)
        }

        return view


    }


    //change textviews shape while switching from one to another
    private fun changeToSelectedColor(view: View, view2: View, view3: View) {
        view.setBackgroundResource(R.drawable.button_shape_two)
        view2.setBackgroundResource(R.drawable.button_shape_three)
        view3.setBackgroundResource(R.drawable.button_shape_three)

    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }


    @SuppressLint("NewApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }

            filePath = data.data
            try {
                viewModel.uploadPictureToFirebaseStorage(filePath!!, "ProfileImages")
            } catch (e: Exception) {
                Log.e("onActivityResult: ", "${e.message}")
            }

            try {
                val bitmap = when {
                    Build.VERSION.SDK_INT < 28 -> MediaStore.Images.Media.getBitmap(
                        requireContext().contentResolver,
                        filePath
                    ) as Bitmap
                    else -> {
                        val source =
                            ImageDecoder.createSource(requireContext().contentResolver, filePath!!)
                        ImageDecoder.decodeBitmap(source)
                    }
                }

                circleImageView.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }


        }
    }


    private fun replaceFragment(selectedFrag: String) {

        when (selectedFrag) {
            INFO_FRAG_TAG -> childFragmentManager.beginTransaction()
                .replace(
                    R.id.child_fragments_container,
                    InfoFragment()
                )
                .addToBackStack(null)
                .commit()
            PORTFOLIO_FRAG_TAG -> childFragmentManager.beginTransaction()
                .replace(
                    R.id.child_fragments_container,
                    PortfolioFragment()
                )
                .addToBackStack(null)
                .commit()
            EXPERIENCE_FRAG_TAG -> childFragmentManager.beginTransaction()
                .replace(
                    R.id.child_fragments_container,
                    ExperienceFragment()
                )
                .addToBackStack(null)
                .commit()
        }

    }

    private fun setUserData() {
        viewModel.getUserData()
        viewModel.userData.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                username.text = it.data!!.username
                job.text = it.data.title
                Glide.with(this)
                    .load(it.data.picture)
                    .placeholder(R.drawable.ic_image)
                    .into(circleImageView)
            } else
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        })
    }

}