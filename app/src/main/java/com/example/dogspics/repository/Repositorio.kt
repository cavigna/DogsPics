package com.example.dogspics.repository

import com.example.dogspics.dao.PerroDao
import com.example.dogspics.model.ListadoRazaDB
import com.example.dogspics.network.DogApiService

class Repositorio(private val api: DogApiService, private val dao: PerroDao) {

    suspend fun listadoRazaAPI() = api.listadoPerroAPI().message
    suspend fun imagenesPorRaza(nombreRaza : String) = api.imagenesPorRaza(nombreRaza)

    suspend fun agregarListaRazaDB(listadoPerro: List<ListadoRazaDB>)
    = dao.agregarListadoRazasDB(listadoPerro)

    fun listadoRazasDB() = dao.listadoDeRazaDB()
}