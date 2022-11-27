package com.orgnzphts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orgnzphts.AppApplication
import com.orgnzphts.model.Bucket
import com.orgnzphts.model.Photo
import com.orgnzphts.type.PhotoType

class SliderViewModel : ViewModel() {
    private val mediaService = AppApplication.mediaService!!
    private val bucketMap = AppApplication.bucketMap

    private val bucket = MutableLiveData<Bucket>()
    val photoList = MutableLiveData<ArrayList<Photo>>()

    fun selectBucketByIndex(keyIndex : Int) {
        bucket.value = bucketMap.values.toList()[keyIndex]
        photoList.value = mediaService.getPhotoListByBucket(bucket.value, 500)
        AppApplication.initList() // clear favorite/delete List
    }

    fun getBucketNameList() : ArrayList<String> {
        val bucketNmList = ArrayList<String>()
        bucketMap.values.toList().forEach {
            bucketNmList.add(it.bucketDisplayName)
        }
        return bucketNmList
    }

    fun favorite(photo: Photo){
        photo.type = PhotoType.FAVORITE
        AppApplication.favoriteList.add(photo)
        AppApplication.deleteList.remove(photo)

        println(AppApplication.favoriteList)
        println(AppApplication.deleteList)
    }

    fun delete(photo: Photo){
        photo.type = PhotoType.DELETE
        AppApplication.deleteList.add(photo)
        AppApplication.favoriteList.remove(photo)

        println(AppApplication.favoriteList)
        println(AppApplication.deleteList)
    }
}