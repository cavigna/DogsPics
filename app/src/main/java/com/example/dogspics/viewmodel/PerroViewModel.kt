package com.example.dogspics.viewmodel

import androidx.lifecycle.*
import com.example.dogspics.model.ListadoRazaDB
import com.example.dogspics.model.PerroFavorito
import com.example.dogspics.model.PerroImagenes
import com.example.dogspics.repository.Repositorio
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class PerroViewModel(private val repositorio: Repositorio) : ViewModel() {


    val listadoRazaDB: LiveData<List<ListadoRazaDB>> = repositorio.listadoRazasDB().asLiveData()

    private var _listadoImagenesPorRaza = MutableLiveData<PerroImagenes>()
    val listadoImagenesPorRaza: LiveData<PerroImagenes> = _listadoImagenesPorRaza

    val listadoFavorito = repositorio.listadoFavoritos().asLiveData()

    var razaSeleccionada = ""

    init {
        agregarListadoRaza()
    }

    fun imagenesPorRaza(nombreRaza:String){

        viewModelScope.launch(IO) {
            _listadoImagenesPorRaza.postValue(repositorio.imagenesPorRaza(nombreRaza))
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

    fun agregarFavorito(perro: PerroFavorito){

        viewModelScope.launch {
            repositorio.agegarFavorito(perro)
        }

    }

    fun borrarFavorito(perro: PerroFavorito){
        viewModelScope.launch {
            repositorio.borrarFavorito(perro)
        }
    }



}