package com.example.dogspics.fragments.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.dogspics.application.PerroApplication
import com.example.dogspics.databinding.FragmentHomeBinding
import com.example.dogspics.viewmodel.PerroModelFactory
import com.example.dogspics.viewmodel.PerroViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

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
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewModel.agregarListadoRaza()

        return binding.root


    }


}