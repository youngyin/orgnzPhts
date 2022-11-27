package com.orgnzphts.viewmodel

import androidx.lifecycle.ViewModel
import com.orgnzphts.AppApplication

class TrashViewModel : ViewModel() {
    private val mediaService = AppApplication.mediaService!!
    val photoList = mediaService.getPhotoList(100)
}