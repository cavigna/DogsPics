package com.example.dogspics.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dogspics.model.ListadoRazaDB
import kotlinx.coroutines.flow.Flow


@Dao
interface PerroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarListadoRazasDB(listadoRazaDB: List<ListadoRazaDB>)

    @Query("SELECT * FROM listado_razas")
    fun listadoDeRazaDB(): Flow<List<ListadoRazaDB>>
}