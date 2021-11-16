package com.example.dogspics.fragments.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.dogspics.R
import com.example.dogspics.application.PerroApplication
import com.example.dogspics.databinding.FragmentHomeBinding
import com.example.dogspics.model.PerroFavorito
import com.example.dogspics.network.NetworkResult
import com.example.dogspics.viewmodel.PerroModelFactory
import com.example.dogspics.viewmodel.PerroViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var appPerro: Application

    private lateinit var searchView: SearchView

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

        viewModel.perroRandom.observe(viewLifecycleOwner, {
            with(binding){
                imageViewRandom.load(it.imgUrl)
                val raza = it.imgUrl.substringAfter("breeds/").substringBefore('/')
                val imgUrl = it.imgUrl
                imageViewRandom.setOnLongClickListener {
                    viewModel.agregarFavorito(PerroFavorito(raza = raza, imagenUrl = imgUrl ))
                    Toast.makeText(requireContext(), "Perro Agregado", Toast.LENGTH_SHORT).show()


                    true
                }

            }
        })
        searchView = binding.searchView

        searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val razaBuscada =  viewModel.razaSeleccionada
                viewModel.buscadorRazaDB(p0!!)
               if (razaBuscada != ""){
                    val bundle = Bundle()
                    bundle.putString("raza", p0)
                    findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)

                }else{
                    Toast.makeText(requireContext(), "No tenemos esa raza man", Toast.LENGTH_SHORT).show()

                }
                return true

            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })





        return binding.root


    }


}



