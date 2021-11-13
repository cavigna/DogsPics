package com.example.dogspics.fragments.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.dogspics.R
import com.example.dogspics.application.PerroApplication
import com.example.dogspics.databinding.FragmentDetailsBinding
import com.example.dogspics.databinding.FragmentFavBinding
import com.example.dogspics.viewmodel.PerroModelFactory
import com.example.dogspics.viewmodel.PerroViewModel

class FavFragment : Fragment() {

    private lateinit var binding: FragmentFavBinding
    private lateinit var appPerro: Application

    private val viewModel: PerroViewModel by activityViewModels {
        PerroModelFactory((appPerro as PerroApplication).repositorio)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appPerro = requireActivity().application

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavBinding.inflate(layoutInflater, container, false)





        return binding.root
    }


}