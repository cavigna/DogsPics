package com.example.dogspics.network

import com.example.dogspics.model.ListadoRazasAPI
import com.example.dogspics.model.PerroImagenes
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {

    @GET("breeds/list") ///breed/
    suspend fun listadoPerroAPI(): ListadoRazasAPI

    @GET("/breed/{nombreRaza}/images")
    suspend fun imagenesPorRaza(@Path("nombreRaza") nombreRaza:String) : PerroImagenes
}