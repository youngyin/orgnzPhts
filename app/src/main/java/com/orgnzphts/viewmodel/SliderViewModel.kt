package com.orgnzphts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orgnzphts.AppApplication
import com.orgnzphts.model.Bucket
import com.orgnzphts.model.Photo

class SliderViewModel : ViewModel() {
    private val mediaService = AppApplication.mediaService!!
    private val bucketMap = AppApplication.bucketMap

    private val bucket = MutableLiveData<Bucket>()
    val photoList = MutableLiveData<ArrayList<Photo>>()

    fun selectBucketByIndex(keyIndex : Int) {
        bucket.value = bucketMap.values.toList()[keyIndex]
        photoList.value = mediaService.getPhotoListByBucket(bucket.value, 5)
    }

    fun getBucketNameList() : ArrayList<String> {
        val bucketNmList = ArrayList<String>()
        bucketMap.values.toList().forEach {
            bucketNmList.add(it.bucketDisplayName)
        }
        return bucketNmList
    }
}