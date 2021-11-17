package com.example.dogspics.model


import com.google.gson.annotations.SerializedName

data class PerroRandom(
    @SerializedName("message")
    var imgUrl: String = "",
    @SerializedName("status")
    var status: String = ""
)


/*
{
    "message": "https://images.dog.ceo/breeds/keeshond/n02112350_9580.jpg",
    "status": "success"
}
 */