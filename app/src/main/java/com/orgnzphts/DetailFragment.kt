package com.orgnzphts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.orgnzphts.model.Photo

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val photo = arguments?.getSerializable("photo") as? Photo
        val root = inflater.inflate(R.layout.fragment_detail, container, false)

        Glide.with(root)
            .load(photo?.data)
            .into(root.findViewById(R.id.iv_photo))

        return root
    }
}