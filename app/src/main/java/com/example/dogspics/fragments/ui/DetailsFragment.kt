package com.example.dogspics.fragments.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogspics.adapter.FotosListAdapter
import com.example.dogspics.adapter.FotosListAdapter.MiEscuchador
import com.example.dogspics.application.PerroApplication

import com.example.dogspics.databinding.FragmentDetailsBinding
import com.example.dogspics.model.PerroFavorito
import com.example.dogspics.viewmodel.PerroModelFactory
import com.example.dogspics.viewmodel.PerroViewModel


class DetailsFragment : Fragment(), MiEscuchador {

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
        val adapter = FotosListAdapter(this)


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter

        viewModel.razaSeleccionada = raza!!

        viewModel.imagenesPorRaza(raza!!)

        viewModel.listadoImagenesPorRaza.observe(viewLifecycleOwner, {
            adapter.submitList(it.imagenes)

        })

        return binding.root


    }

    override fun alHacerClick(url: String) {
        Log.i("miescuchador", url)
        val perro = PerroFavorito(raza = viewModel.razaSeleccionada, imagenUrl = url)
        viewModel.agregarFavorito(perro)

        Toast.makeText(requireContext(), "Tenés un nuevo Perrito favortio!!", Toast.LENGTH_SHORT)
            .show()

    }


}