package com.example.dogspics.application

import android.app.Application
import com.example.dogspics.db.BaseDeDatos
import com.example.dogspics.network.DogApiService
import com.example.dogspics.repository.Repositorio
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PerroApplication : Application() {

    private val retrofitCliente by lazy {
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiService::class.java)

    }

    private val baseDeDatos by lazy { BaseDeDatos.getDataBase(this) }
//
    val repositorio by lazy { Repositorio(retrofitCliente, baseDeDatos.dao()) }


}