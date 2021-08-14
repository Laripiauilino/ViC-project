package com.larissa.integrativeproject.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.larissa.integrativeproject.databinding.FragmentNotFoundBinding


class NotFoundFragment : Fragment() {

    private var binding: FragmentNotFoundBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotFoundBinding.inflate(inflater, container, false)
        return binding?.root
    }
}