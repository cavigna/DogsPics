package com.example.dogspics.fragments.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogspics.adapter.FotosListAdapter
import com.example.dogspics.application.PerroApplication

import com.example.dogspics.databinding.FragmentDetailsBinding
import com.example.dogspics.viewmodel.PerroModelFactory
import com.example.dogspics.viewmodel.PerroViewModel


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
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
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        val bundle = arguments
        val raza = bundle?.getString("raza")
        val recyclerView = binding.recyclerImagenes
        val adapter = FotosListAdapter()
//        val adapter = FotosListAdapter(object : FotosListAdapter.MiEscuchador{
//            override fun alHacerClick(raza: String): String {
//                TODO("Not yet implemented")
//            }
//
//        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        viewModel.imagenesPorRaza(raza!!)

        viewModel.listadoImagenesPorRaza.observe(viewLifecycleOwner, {
            adapter.submitList(it.imagenes)
        })

        return binding.root


    }


}