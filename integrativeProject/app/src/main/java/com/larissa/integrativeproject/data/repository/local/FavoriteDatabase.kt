package com.larissa.integrativeproject.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.larissa.integrativeproject.data.model.Movies
import com.larissa.integrativeproject.utils.DataTypeConverter

@Database(entities = [Movies::class], version = 1)
@TypeConverters(DataTypeConverter::class)

abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

}
