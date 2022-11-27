package com.orgnzphts.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.orgnzphts.model.Photo
import com.orgnzphts.model.PhotoType

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo WHERE type = (:photoType)")
    fun findByType(photoType: PhotoType) : List<Photo>

    @Insert
    fun insert(vararg photo : Photo)

    @Insert
    fun delete(vararg photo: Photo)
}