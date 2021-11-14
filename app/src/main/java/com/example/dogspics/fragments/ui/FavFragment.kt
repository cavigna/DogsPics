package com.example.dogspics.fragments.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogspics.R
import com.example.dogspics.adapter.FavListAdapter
import com.example.dogspics.application.PerroApplication
import com.example.dogspics.databinding.FragmentDetailsBinding
import com.example.dogspics.databinding.FragmentFavBinding
import com.example.dogspics.model.PerroFavorito
import com.example.dogspics.viewmodel.PerroModelFactory
import com.example.dogspics.viewmodel.PerroViewModel

class FavFragment : Fragment(), FavListAdapter.MiEscuchadorFav {

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

        val recyclerView = binding.recyclerViewFav
        val adapter = FavListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.listadoFavorito.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        return binding.root
    }

    override fun alHacerClick(perroFavorito: PerroFavorito) {
        viewModel.borrarFavorito(perroFavorito)
        Toast.makeText(requireContext(), "Perrito Eliminado", Toast.LENGTH_SHORT).show()
    }


}