package com.example.photoeditor.ui.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.photoeditor.R
import com.example.photoeditor.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    lateinit var mBinding: FragmentResultBinding
    val args : ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = FragmentResultBinding.inflate(inflater,container,false)

        val uri = args.uri

        mBinding.ivResultImage.setImageURI(Uri.parse(uri))

        return mBinding.root
    }
}