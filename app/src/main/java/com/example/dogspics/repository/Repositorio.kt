package com.example.dogspics.repository

import com.example.dogspics.dao.PerroDao
import com.example.dogspics.model.ListadoRazaDB
import com.example.dogspics.model.PerroFavorito
import com.example.dogspics.network.DogApiService

class Repositorio(private val api: DogApiService, private val dao: PerroDao) {

    suspend fun listadoRazaAPI() = api.listadoPerroAPI().message
    suspend fun imagenesPorRaza(nombreRaza : String) = api.imagenesPorRaza(nombreRaza)

    suspend fun agregarListaRazaDB(listadoPerro: List<ListadoRazaDB>)
    = dao.agregarListadoRazasDB(listadoPerro)

    fun listadoRazasDB() = dao.listadoDeRazaDB()

    suspend fun agegarFavorito(perro: PerroFavorito) = dao.agregarFavorito(perro)
    suspend fun borrarFavorito(perro: PerroFavorito) = dao.eliminarFavorito(perro)

    fun listadoFavoritos() = dao.listadoDeFavoritos()

    suspend fun perroRandom() =  api.perroRandom()

    suspend fun buscarRazaAPI(busqueda:String) = api.buscarRaza(busqueda)
    suspend fun buscarRazaDB(busqueda:String) = dao.buscadorRaza(busqueda)
}