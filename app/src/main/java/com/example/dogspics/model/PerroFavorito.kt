package com.example.dogspics.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritos")
data class PerroFavorito(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    val raza: String,
    val imagenUrl : String,


)
