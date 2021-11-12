package com.example.dogspics.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listado_razas")
data class ListadoRazaDB(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val raza:String = "",
    //var subRaza : List<String> = listOf("")

)
