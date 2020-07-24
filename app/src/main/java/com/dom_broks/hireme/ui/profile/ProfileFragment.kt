package com.dom_broks.hireme.ui.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dom_broks.hireme.R
import com.dom_broks.hireme.data.FirebaseSource
import com.dom_broks.hireme.data.Repository
import com.dom_broks.hireme.ui.auth.viewModel.AuthViewModelFactory
import kotlinx.android.synthetic.main.profile_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.io.IOException

class ProfileFragment : Fragment(){



    companion object {
        fun newInstance() = ProfileFragment()
    }




    private lateinit var viewModel: ProfileViewModel
    private lateinit var profileViewModelFactory: ProfileViewModelFactory
    private val PICK_IMAGE_REQUEST = 1
    private var filePath: Uri? = null


    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.profile_fragment, container, false)


        val infoBtn: TextView = view.findViewById(R.id.infoBtn)
        val portfolioBtn: TextView = view.findViewById(R.id.portfolioBtn)
        val experienceBtn: TextView = view.findViewById(R.id.experienceBtn)
        portfolioBtn.setBackgroundResource(R.drawable.button_shape_two)
        val avatar: ImageView = view.findViewById(R.id.circleImageView)
        avatar.setOnClickListener(View.OnClickListener {
            launchGallery()
        })



        infoBtn.setOnClickListener(View.OnClickListener {
            changeToSelectedColor(infoBtn, portfolioBtn, experienceBtn)
        })
        experienceBtn.setOnClickListener {
            changeToSelectedColor(experienceBtn, portfolioBtn, infoBtn)
        }
        portfolioBtn.setOnClickListener(View.OnClickListener {
            changeToSelectedColor(portfolioBtn, infoBtn, experienceBtn)
        })

        return view


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val firebaseSource = FirebaseSource()
        val repository = Repository(firebaseSource)
        profileViewModelFactory= ProfileViewModelFactory(repository)
        viewModel=ViewModelProviders.of(this,profileViewModelFactory).get(ProfileViewModel::class.java)

     //   viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
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

            viewModel.uploadPictureToFirebaseStorage(filePath!!,"ProfileImages")



            try {
                // val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                //  val source = ImageDecoder.createSource(activity!!.contentResolver, filePath!!)
                //   val bitmap = ImageDecoder.decodeBitmap(source)

                val bitmap = when {
                    Build.VERSION.SDK_INT < 28 -> MediaStore.Images.Media.getBitmap(
                        context!!.contentResolver,
                        filePath

                    ) as Bitmap
                    else -> {
                        val source =
                            ImageDecoder.createSource(context!!.contentResolver, filePath!!)
                        ImageDecoder.decodeBitmap(source)
                    }

                }

                circleImageView.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }


        }
    }

}