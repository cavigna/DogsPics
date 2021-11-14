package com.example.dogspics.model


import com.google.gson.annotations.SerializedName

data class PerroRandom(
    @SerializedName("message")
    var imgUrl: String = "",
    @SerializedName("status")
    var status: String = ""
)