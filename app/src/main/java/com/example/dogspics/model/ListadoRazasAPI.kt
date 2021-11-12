package com.example.dogspics.model


import com.google.gson.annotations.SerializedName

data class ListadoRazasAPI(
    @SerializedName("message")
    var message: List<String> = listOf(),
    @SerializedName("status")
    var status: String = ""
)