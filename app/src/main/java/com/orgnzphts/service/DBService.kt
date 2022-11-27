package com.orgnzphts.service

import android.content.Context
import androidx.room.Room
import com.orgnzphts.model.Photo
import com.orgnzphts.model.PhotoType
import com.orgnzphts.room.AppDatabase

class DBService(private val context: Context) {

    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()

    private val photoDao = db.photoDao()

    fun findByTpe(type: PhotoType): List<Photo> {
        return photoDao.findByType(type)
    }

    fun insert(photo: Photo){
        photoDao.insert(photo)
    }
}



