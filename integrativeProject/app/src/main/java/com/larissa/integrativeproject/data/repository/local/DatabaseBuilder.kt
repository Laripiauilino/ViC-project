package com.larissa.integrativeproject.data.repository.local

import android.app.Application
import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    private var database: FavoriteDatabase? = null

    fun getFavoriteDatabase(context: Context): FavoriteDatabase? {
        if (database == null) {
            synchronized(FavoriteDatabase::class) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "FavoriteDb"
                ).build()
            }
        }
        return database
    }
}