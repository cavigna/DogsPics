package com.example.dogspics.network

import com.example.dogspics.model.ListadoRazasAPI
import com.example.dogspics.model.PerroImagenes
import com.example.dogspics.model.PerroRandom
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {

    @GET("breeds/list") ///breed/
    suspend fun listadoPerroAPI(): ListadoRazasAPI

    @GET("breed/{nombreRaza}/images")
    suspend fun imagenesPorRaza(@Path("nombreRaza") nombreRaza:String) : PerroImagenes

    //https://dog.ceo/api/breeds/image/random

    @GET("breeds/image/random")
    suspend fun perroRandom(): PerroRandom


    @GET("breed/{nombreRaza}/images")
    suspend fun buscarRaza(@Path("nombreRaza") nombreRaza:String) : Response<NetworkResult<PerroImagenes>>

}