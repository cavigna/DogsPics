package com.example.dogspics.model


import com.google.gson.annotations.SerializedName

data class PerroImagenes(
    @SerializedName("message")
    var imagenes: List<String> = listOf(),
    @SerializedName("status")
    var status: String = ""
)