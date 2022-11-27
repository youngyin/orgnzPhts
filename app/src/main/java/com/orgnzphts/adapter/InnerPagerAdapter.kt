package com.orgnzphts.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orgnzphts.R
import com.orgnzphts.model.Photo
import com.orgnzphts.type.PhotoType

class InnerPagerAdapter : RecyclerView.Adapter<InnerPagerAdapter.ViewHolder>() {
    private val database = ArrayList<Photo?>()

    fun setPhoto(photo: Photo){
        database.clear()
        database.add(null)
        database.add(photo)
        database.add(null)
        notifyItemChanged(1)
    }

    fun setPhotoType(type: PhotoType) {
        database[1]?.type = type
        notifyItemChanged(1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(database[position])
    }

    override fun getItemCount(): Int = database.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("ClickableViewAccessibility", "CheckResult", "SetTextI18n")
        fun bind(photo: Photo?) {
            photo ?: return

            itemView.findViewById<TextView>(R.id.tv_photo).text = "${photo.page}/${photo.pageTot}"

            when (photo.type){
                PhotoType.FAVORITE ->
                    Glide.with(itemView.context).load(R.drawable.ic_baseline_bookmark_24)
                    .into(itemView.findViewById(R.id.iv_mark))

                PhotoType.DELETE->
                    Glide.with(itemView.context).load(R.drawable.ic_baseline_delete_24)
                    .into(itemView.findViewById(R.id.iv_mark))
            }

            Glide.with(itemView.context)
                .load(photo.data)
                //.placeholder()
                .error(R.drawable.ic_baseline_bookmark_24)
                .into(itemView.findViewById(R.id.iv_photo))

        }
    }
}