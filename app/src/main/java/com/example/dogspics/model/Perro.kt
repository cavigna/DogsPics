package com.example.dogspics.model

import androidx.room.Entity


data class Perro(
    val raza : String = "",
    val subRaza: String = "",
    val imagenes : List<String> = listOf(),
)
