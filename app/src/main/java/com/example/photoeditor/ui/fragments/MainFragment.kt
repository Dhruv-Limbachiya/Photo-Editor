package com.example.photoeditor.ui.fragments

import android.Manifest
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.photoeditor.databinding.FragmentMainBinding
import com.example.photoeditor.utils.getTempFileUri

class MainFragment : Fragment(){

    lateinit var mBinding: FragmentMainBinding

    private var mUri : Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = FragmentMainBinding.inflate(inflater,container,false)

        mBinding.ivEdit.setOnClickListener {
            launchGallery()
        }

        mBinding.ivCamera.setOnClickListener {
            launchCamera()
        }

        return mBinding.root
    }

    /**
     * Method will launch Content Launcher to choose images.
     */
    private fun launchGallery() {
        selectImageLauncher.launch("image/*") // Launch the selectImageLauncher with "image/*" type(only images).
    }

    /**
     * Method will launch Camera to take picture.
     */
    private fun launchCamera() {
        mUri = getTempFileUri(requireContext())
        cameraLauncher.launch(mUri) // Launch camera with the temp uri.
    }

    // Content Launcher.
    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            Toast.makeText(requireContext(),uri.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    // Camera Launcher.
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
        if(isSuccess) {
            mUri?.let {
                Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
}