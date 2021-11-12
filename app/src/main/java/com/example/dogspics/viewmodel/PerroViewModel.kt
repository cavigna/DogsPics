package com.example.dogspics.viewmodel

import androidx.lifecycle.*
import com.example.dogspics.model.ListadoRazaDB
import com.example.dogspics.repository.Repositorio
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class PerroViewModel(private val repositorio: Repositorio) : ViewModel() {


    val listadoRazaDB: LiveData<List<ListadoRazaDB>> = repositorio.listadoRazasDB().asLiveData()

    val listadoImagenesPorRaza = MutableLiveData<List<String>>()

    fun imagenesPorRaza(nombreRaza:String){

        viewModelScope.launch(IO) {
            listadoImagenesPorRaza.value = repositorio.imagenesPorRaza(nombreRaza).imagenes
        }

    }




    fun agregarListadoRaza() {
        viewModelScope.launch {
            val listado = repositorio.listadoRazaAPI()
            val listadoRazaDB = mutableListOf<ListadoRazaDB>()

            for (s in listado) {
                listadoRazaDB.add(ListadoRazaDB(raza = s))
            }



            repositorio.agregarListaRazaDB(listadoRazaDB)


        }
    }

}