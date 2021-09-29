package com.example.photoeditor.ui.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.photoeditor.databinding.FragmentResultBinding
import com.example.photoeditor.utils.Constants.FB_PACKAGE_NAME
import com.example.photoeditor.utils.Constants.INSTA_PACKAGE_NAME
import com.example.photoeditor.utils.Constants.WHATSAPP_PACKAGE_NAME


class ResultFragment : Fragment() {

    lateinit var mBinding: FragmentResultBinding

    private val args: ResultFragmentArgs by navArgs()

    lateinit var intent: Intent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = FragmentResultBinding.inflate(inflater, container, false)

        val uri = args.uri

        mBinding.ivResultImage.setImageURI(Uri.parse(uri))

        eventListeners(uri)

        return mBinding.root
    }

    /**
     * Listen all social media & share button on click listeners.
     */
    private fun eventListeners(uri: String) {
        mBinding.ivShare.setOnClickListener {
            shareImage(INSTA_PACKAGE_NAME, "Instagram", uri,true)
        }

        mBinding.ivWhatsapp.setOnClickListener {
            shareImage(WHATSAPP_PACKAGE_NAME, "Whatsapp", uri)
        }

        mBinding.ivFb.setOnClickListener {
            shareImage(FB_PACKAGE_NAME, "Facebook", uri)
        }

        mBinding.ivInsta.setOnClickListener {
            shareImage(INSTA_PACKAGE_NAME, "Instagram", uri)
        }

        mBinding.ivHome.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    /**
     * Method responsible for launch and share image to different social media platforms.
     */
    private fun shareImage(
        packageName: String,
        appName: String,
        uri: String,
        isDefaultShare: Boolean = false
    ) {
        intent = Intent(Intent.ACTION_SEND)
        intent.setPackage(packageName);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(uri))
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            if (isDefaultShare) {
                startActivity(Intent.createChooser(intent,"Share image"))
            } else {
                startActivity(intent)
            }
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                "$appName have not been installed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}