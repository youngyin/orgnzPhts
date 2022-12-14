package com.orgnzphts

import android.app.Application
import com.orgnzphts.model.Bucket
import com.orgnzphts.model.Photo
import com.orgnzphts.service.MediaService
import com.orgnzphts.service.PermissionService

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // permission
        val permissionService = PermissionService(baseContext)
        permissionService.create()

        // get Image file list
        mediaService = MediaService(contentResolver)
        bucketMap = mediaService!!.getBucketMap()
    }

    companion object {
        var mediaService : MediaService? = null
        var bucketMap : HashMap<String, Bucket> = HashMap()

        val deleteList = ArrayList<Photo>()
        val favoriteList = ArrayList<Photo>()

        // todo : use room
        fun initList(){
            deleteList.clear()
            favoriteList.clear()
        }
    }
}