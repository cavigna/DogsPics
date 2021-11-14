package com.example.dogspics.viewmodel

import androidx.lifecycle.*
import com.example.dogspics.model.ListadoRazaDB
import com.example.dogspics.model.PerroFavorito
import com.example.dogspics.model.PerroImagenes
import com.example.dogspics.model.PerroRandom
import com.example.dogspics.network.NetworkResult
import com.example.dogspics.repository.Repositorio
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception

class PerroViewModel(private val repositorio: Repositorio) : ViewModel() {


    val listadoRazaDB: LiveData<List<ListadoRazaDB>> = repositorio.listadoRazasDB().asLiveData()

    private var _listadoImagenesPorRaza = MutableLiveData<PerroImagenes>()
    val listadoImagenesPorRaza: LiveData<PerroImagenes> = _listadoImagenesPorRaza

    val listadoFavorito = repositorio.listadoFavoritos().asLiveData()

    var razaSeleccionada = ""

    private var _perroRandom = MutableLiveData<PerroRandom>()
    val perroRandom: LiveData<PerroRandom> = _perroRandom

    var respuestaBuscar = MutableLiveData<NetworkResult<PerroImagenes>>()

    init {
        agregarListadoRaza()

        traerPerroRandom()
    }

    fun imagenesPorRaza(nombreRaza: String) {

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

    fun agregarFavorito(perro: PerroFavorito) {

        viewModelScope.launch {
            repositorio.agegarFavorito(perro)
        }

    }

    fun borrarFavorito(perro: PerroFavorito) {
        viewModelScope.launch {
            repositorio.borrarFavorito(perro)
        }
    }

    fun traerPerroRandom() {
        viewModelScope.launch(IO) {
            _perroRandom.postValue(repositorio.perroRandom())

        }
    }


    fun buscarRaza(nombreRaza: String){
        respuestaBuscar.postValue(NetworkResult.Loading())

        viewModelScope.launch {
            try {
                val respuestaExitosa = repositorio.buscarRazaAPI(nombreRaza).body()!!
                respuestaBuscar.postValue(respuestaExitosa)
            }catch (e: Exception){
                respuestaBuscar.postValue(NetworkResult.Error(e.message))
            }
        }

    }

    fun buscadorRazaDB(nombreRaza: String){
        viewModelScope.launch {
            razaSeleccionada = repositorio.buscarRazaDB(nombreRaza).asLiveData().value?.get(0)?.raza.toString()
        }
    }


}