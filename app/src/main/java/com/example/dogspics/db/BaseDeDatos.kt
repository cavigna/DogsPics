package com.example.dogspics.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogspics.dao.PerroDao
import com.example.dogspics.model.ListadoRazaDB
import com.example.dogspics.model.PerroFavorito


@Database(entities = [ListadoRazaDB::class, PerroFavorito::class], version = 1, exportSchema = false)
abstract class BaseDeDatos : RoomDatabase() {
    abstract fun dao() : PerroDao

    companion object {

        @Volatile
        private var INSTANCE: BaseDeDatos? = null

        fun getDataBase(context: Context): BaseDeDatos {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDeDatos::class.java,
                    "perros"
                )
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}