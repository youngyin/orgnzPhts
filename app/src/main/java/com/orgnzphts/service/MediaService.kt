package com.orgnzphts.service

import android.content.ContentResolver
import android.database.Cursor
import android.provider.MediaStore
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import com.orgnzphts.model.Bucket
import com.orgnzphts.model.Photo

class MediaService(
    private val resolver : ContentResolver
) {
    private var queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

    private val what = arrayOf(
        MediaStore.Images.ImageColumns.BUCKET_ID,
        MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DATE_ADDED,
        MediaStore.Images.Media.DATE_MODIFIED,
    )

    fun getBucketMap() : HashMap<String, Bucket> {
        val orderBy = "${MediaStore.Images.ImageColumns.DATE_ADDED} DESC"
        val bucketMap = HashMap<String, Bucket>()
        val bucketIdList = ArrayList<String>()

        while (true) {
            val questionMark = arrayListOf<String>()
            repeat(bucketMap.count()) { questionMark.add("?") }
            val where = "${MediaStore.Images.ImageColumns.BUCKET_ID} not in (${questionMark.joinToString(", ")})"

            val photo = getPhoto(what, where, bucketIdList.toTypedArray(), orderBy, 1).firstOrNull() ?: break
            val bucket = Bucket(photo.bucketId, photo.bucketDisplayName)

            bucketIdList.add(bucket.bucketId)
            bucketMap[bucket.bucketId] = bucket
        }

        return bucketMap
    }

    fun getPhotoList(count: Int) : ArrayList<Photo> {
        val orderBy = "${MediaStore.Images.ImageColumns.DATE_ADDED} DESC, ${MediaStore.Images.Media._ID}"
        return getPhoto(what, null, null, orderBy, count)
    }

    fun getPhotoListByBucket(bucket: Bucket?, count: Int) : ArrayList<Photo> {
        val orderBy = "${MediaStore.Images.ImageColumns.DATE_ADDED} DESC, ${MediaStore.Images.Media._ID}"
        val where = "${MediaStore.Images.ImageColumns.BUCKET_ID} = '${bucket?.bucketId}'"
        return getPhoto(what, where, null, orderBy, count)
    }

    private fun getPhoto(what : Array<String>, where : String?, whereArgs : Array<String>?, orderBy : String, count : Int) : ArrayList<Photo> {
        var cursor : Cursor? = null
        val photoList = arrayListOf<Photo>()
        var cnt = count

        try {
            cursor = resolver.query(queryUri, what, where, whereArgs, orderBy)
                ?: throw Exception("잘못된 쿼리이거나, 권한이 허용되지 않았습니다.")

            if (cursor.moveToFirst()) {
                do {
                    // find index
                    val bucketIdIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
                    val bucketNmIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                    val dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    val dateAddedIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
                    val dateModifiedIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED)
                    val idIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

                    // find value
                    val bucketId = cursor.getStringOrNull(bucketIdIndex) ?: throw Exception("id=null");
                    val bucketName = cursor.getStringOrNull(bucketNmIndex) ?: throw Exception("name=null")
                    val data = cursor.getStringOrNull(dataIndex) ?: throw  Exception("data=null")
                    val dateAdded = cursor.getLongOrNull(dateAddedIndex)
                    val dateModified = cursor.getLongOrNull(dateModifiedIndex)
                    val idx = cursor.getLongOrNull(idIndex) ?: throw  Exception("id = null")

                    photoList.add(Photo(bucketId, bucketName, idx, data, dateAdded, dateModified))

                } while (--cnt>0 && cursor.moveToNext())
            }
        } catch (e : SecurityException){
            e.stackTrace
        } catch (e : Exception){
            e.stackTrace
        } finally {
            cursor?.close()
        }
        return photoList
    }

}