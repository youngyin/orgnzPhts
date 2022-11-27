package com.orgnzphts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.orgnzphts.adapter.PagerAdapter
import com.orgnzphts.databinding.FragmentSliderBinding
import com.orgnzphts.listener.SliderListener
import com.orgnzphts.model.Photo
import com.orgnzphts.viewmodel.SliderViewModel

class SliderFragment : Fragment() {

    private var _binding: FragmentSliderBinding? = null
    private val binding get() = _binding!!
    private lateinit var model : SliderViewModel
    private lateinit var adapter : PagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model = ViewModelProvider(this)[SliderViewModel::class.java]
        _binding = FragmentSliderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initObserver()
        initToolBar()
        initView()

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
        binding.vpSlide.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            private var prevPosition = 0

            override fun onPageSelected(position: Int) {
                if (prevPosition < position) {
                } else if (prevPosition > position){
                }

                prevPosition = position
            }
        })

        // spinner
        with(binding.spinner){
            this.adapter = ArrayAdapter(
                requireContext(),
                androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                model.getBucketNameList()
            )
            this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    view?.let {
                        model.selectBucketByIndex(position)
                    }
                }

                override fun onNothingSelected(arg0: AdapterView<*>?) {}
            }
        }
    }

    private fun initObserver() {
        model.photoList.observe(viewLifecycleOwner) {

            adapter = PagerAdapter(requireActivity(), it,
                object : SliderListener {
                    override fun showPrev() : Boolean {
                        if (binding.vpSlide.currentItem != 0) {
                            binding.vpSlide.currentItem -= 1
                            return true
                        }

                        return false
                    }

                    override fun showNext() : Boolean{
                        if (binding.vpSlide.currentItem != adapter.itemCount-1) {
                            binding.vpSlide.currentItem += 1
                            return true
                        }

                        return false
                    }

                    override fun favorite(photo: Photo) {
                        Toast.makeText(requireContext(), "favorite!", Toast.LENGTH_SHORT).show()
                        model.favorite(photo)
                    }

                    override fun delete(photo: Photo) {
                        Toast.makeText(requireContext(), "delete!", Toast.LENGTH_SHORT).show()
                        model.delete(photo)
                    }
            })
            binding.vpSlide.adapter = adapter
        }
    }
}