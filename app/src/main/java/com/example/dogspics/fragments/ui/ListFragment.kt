package com.example.dogspics.fragments.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogspics.R
import com.example.dogspics.adapter.RazaListAdapter
import com.example.dogspics.application.PerroApplication
import com.example.dogspics.databinding.FragmentHomeBinding
import com.example.dogspics.databinding.FragmentListBinding
import com.example.dogspics.viewmodel.PerroModelFactory
import com.example.dogspics.viewmodel.PerroViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
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
        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        val recyclerView = binding.recyclerViewRaza
        val adapter = RazaListAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        viewModel.listadoRazaDB.observe(viewLifecycleOwner, {
             adapter.submitList(it)

            viewModel.razaSeleccionada = adapter.nombreRazaElegida

        })


        return binding.root
    }


}