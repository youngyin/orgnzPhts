package com.orgnzphts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orgnzphts.R
import com.orgnzphts.listener.ClickListener
import com.orgnzphts.model.Photo

class ImageRecyclerAdapter : RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder>() {
    var database = ArrayList<Photo>()
    var clickListener : ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(database[position])
    }

    override fun getItemCount(): Int = database.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: Photo) {
            itemView.setOnClickListener {
                clickListener?.click(photo)
            }

            Glide.with(itemView.context)
                .load(photo.data)
                //.placeholder()
                .error(R.drawable.ic_baseline_bookmark_24)
                .into(itemView.findViewById(R.id.iv_photo))
        }
    }
}