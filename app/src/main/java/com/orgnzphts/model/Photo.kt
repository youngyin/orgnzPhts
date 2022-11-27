package com.orgnzphts.model

import com.orgnzphts.type.PhotoType
import java.io.Serializable

class Photo (
    val bucketId : String,
    val bucketDisplayName: String,

    val _id : Long,
    val data : String,
    var dateAddedLong: Long? = null,
    val dateModifiedLong: Long? = null,

    var type: PhotoType = PhotoType.NORMAL,

    var page : Long = 0,
    var pageTot: Long = 0
) : Serializable
