package com.orgnzphts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.orgnzphts.adapter.InnerPagerAdapter
import com.orgnzphts.databinding.FragmentPhotoBinding
import com.orgnzphts.listener.SliderListener
import com.orgnzphts.model.Photo
import com.orgnzphts.model.PhotoType

class PhotoFragment(
    var photo: Photo,
    val listener : SliderListener
    ) : Fragment() {

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        val adapter = InnerPagerAdapter()
        adapter.setPhoto(photo)

        binding.vpSlide.adapter = adapter
        binding.vpSlide.setCurrentItem(1, false)

        binding.vpSlide.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> {
                        listener.delete(photo)
                        adapter.setPhotoType(PhotoType.DELETE)

                        listener.showNext()
                        binding.vpSlide.setCurrentItem(1, false)
                    }
                    2 -> {
                        listener.favorite(photo)
                        adapter.setPhotoType(PhotoType.FAVORITE)

                        listener.showNext()
                        binding.vpSlide.setCurrentItem(1, false)
                    }
                }
            }
        })

        return binding.root
    }
}