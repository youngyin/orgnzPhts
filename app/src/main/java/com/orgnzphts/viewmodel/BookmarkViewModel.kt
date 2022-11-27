package com.orgnzphts.viewmodel

import androidx.lifecycle.ViewModel
import com.orgnzphts.AppApplication

class BookmarkViewModel : ViewModel() {
    private val mediaService = AppApplication.mediaService!!
    val photoList = AppApplication.favoriteList
}