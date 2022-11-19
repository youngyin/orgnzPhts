package com.orgnzphts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhotoViewModel : ViewModel() {

    private val _photoPath = MutableLiveData<String>().apply {
        value = ""
    }
    val photoPath : LiveData<String> = _photoPath
}