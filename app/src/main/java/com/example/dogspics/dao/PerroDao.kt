package com.example.dogspics.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dogspics.model.ListadoRazaDB
import com.example.dogspics.model.PerroFavorito
import kotlinx.coroutines.flow.Flow


@Dao
interface PerroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarListadoRazasDB(listadoRazaDB: List<ListadoRazaDB>)

    @Query("SELECT * FROM listado_razas")
    fun listadoDeRazaDB(): Flow<List<ListadoRazaDB>>

    @Insert
    suspend fun agregarFavorito(perroFavorito: PerroFavorito)

    @Delete
    suspend fun eliminarFavorito(perroFavorito: PerroFavorito)

    @Query("SELECT * FROM favoritos")
    fun listadoDeFavoritos():Flow<List<PerroFavorito>>

    @Query("SELECT * FROM listado_razas where raza =:raza")
    fun buscadorRaza(raza:String) : Flow<List<ListadoRazaDB>>


}