package com.example.photoeditor.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.photoeditor.R
import com.example.photoeditor.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    lateinit var mBinding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = FragmentResultBinding.inflate(inflater,container,false)

        return mBinding.root
    }
}