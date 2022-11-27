package com.orgnzphts.listener

import com.orgnzphts.model.Photo

interface ClickListener {
    open fun click(photo: Photo)
}