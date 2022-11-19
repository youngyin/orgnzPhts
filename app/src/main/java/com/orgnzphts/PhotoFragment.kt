package com.orgnzphts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.orgnzphts.databinding.FragmentPhotoBinding
import com.orgnzphts.viewmodel.PhotoViewModel

class PhotoFragment : Fragment() {

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private lateinit var photoViewModel : PhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        photoViewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        val root: View = binding.root

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
        val imageView : ImageView = binding.ivPhoto
        photoViewModel.photoPath.observe(viewLifecycleOwner) {
            imageView.resources
        }
    }
}