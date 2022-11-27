package com.orgnzphts.listener

import com.orgnzphts.model.Photo

interface SliderListener {
    open fun delete(photo: Photo)
    open fun favorite(photo: Photo)
    open fun showPrev(): Boolean
    open fun showNext(): Boolean
}