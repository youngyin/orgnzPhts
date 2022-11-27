package com.orgnzphts.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.orgnzphts.PhotoFragment
import com.orgnzphts.listener.SliderListener
import com.orgnzphts.model.Photo

class PagerAdapter(
    fa : FragmentActivity,
    var database: ArrayList<Photo>,
    private val listener : SliderListener
) : FragmentStateAdapter(fa) {
    private lateinit var fragment : Fragment

    override fun getItemCount(): Int = database.size
    override fun createFragment(position: Int): Fragment {
        fragment = PhotoFragment(database[position], listener)
        return fragment
    }
}