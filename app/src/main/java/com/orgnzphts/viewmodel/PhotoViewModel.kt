package com.orgnzphts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orgnzphts.AppApplication
import com.orgnzphts.model.Photo
import com.orgnzphts.model.Slider

class PhotoViewModel : ViewModel() {
    private val mediaService = AppApplication.mediaService!!
    private val bucketMap = AppApplication.bucketMap
    val bucketNameList = bucketMap.keys.toList()

    private val selectedBucket = MutableLiveData<Photo>()
    val slider = MutableLiveData<Slider>()

    fun selectBucketByIndex(keyIndex : Int) {
        selectBucketByKey(bucketNameList[keyIndex])
    }

    private fun selectBucketByKey(key : String) {
        val photo = bucketMap.getOrDefault(key, null) ?: return
        selectedBucket.value = photo
        val photoList = mediaService.getPhotoList(photo.bucketId)

        //photoList.add(0, photo.emptyPhoto())
        //photoList.add(photo.emptyPhoto())
        slider.value = Slider(0, photo.bucketId, photoList)
    }
}