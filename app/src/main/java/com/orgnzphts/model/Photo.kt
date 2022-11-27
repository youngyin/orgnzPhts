package com.orgnzphts.model

data class Photo(
    val bucketId : String,
    val bucketDisplayName: String,

    val _id : Long,
    val data : String,
    var dateAddedLong: Long? = null,
    val dateModifiedLong: Long? = null,

    var type: PhotoType = PhotoType.NORMAL
)
