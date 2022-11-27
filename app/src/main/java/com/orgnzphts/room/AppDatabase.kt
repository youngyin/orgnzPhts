package com.orgnzphts.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orgnzphts.model.Photo

@Database(entities = [Photo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao() : PhotoDao
}