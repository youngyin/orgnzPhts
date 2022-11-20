package com.orgnzphts

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.orgnzphts.adapter.ImagePagerAdapter
import com.orgnzphts.databinding.FragmentPhotoBinding
import com.orgnzphts.viewmodel.PhotoViewModel

class PhotoFragment : Fragment() {

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private lateinit var model : PhotoViewModel
    private lateinit var adapter : ImagePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model = ViewModelProvider(this)[PhotoViewModel::class.java]
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initToolBar()
        initView()
        initObserver()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initToolBar() {
        requireActivity().actionBar?.hide()

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.navigation_bookmark -> {
                    findNavController().navigate(R.id.action_navigation_home_to_navigation_bookmark)
                    true
                }
                R.id.navigation_trash -> {
                    findNavController().navigate(R.id.action_navigation_home_to_navigation_trash)
                    true
                }
                else -> false
            }
        }
    }

    private fun initView() {
        // viewPager
        adapter = ImagePagerAdapter()
        binding.vpSlide.adapter = adapter
        binding.vpSlide.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
        })

        // spinner
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.transition.R.layout.support_simple_spinner_dropdown_item,
            model.bucketNameList.toList()
        )

        with(binding.spinner){
            this.adapter = adapter
            this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    view?.let {
                        //val selectedItem = arg0?.selectedItem.toString()
                        //photoViewModel.selectBucket(selectedItem)
                        model.selectBucketByIndex(position)
                    }
                }

                override fun onNothingSelected(arg0: AdapterView<*>?) {}
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver() {
        model.slider.observe(viewLifecycleOwner) {
            adapter.database = it.photoList
            adapter.notifyDataSetChanged()
        }
    }
}