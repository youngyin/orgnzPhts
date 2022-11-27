package com.orgnzphts

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.orgnzphts.adapter.ImageRecyclerAdapter
import com.orgnzphts.databinding.FragmentTrashBinding
import com.orgnzphts.listener.ClickListener
import com.orgnzphts.model.Photo
import com.orgnzphts.viewmodel.TrashViewModel

class TrashFragment : Fragment() {

    private var _binding: FragmentTrashBinding? = null
    private val binding get() = _binding!!
    private lateinit var model : TrashViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model = ViewModelProvider(this)[TrashViewModel::class.java]
        _binding = FragmentTrashBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = ImageRecyclerAdapter()
        binding.recycler.adapter = adapter
        adapter.database = model.photoList
        adapter.notifyDataSetChanged()

        adapter.clickListener = object : ClickListener {
            override fun click(photo: Photo) {
                val bundle = bundleOf("photo" to photo)
                findNavController().navigate(R.id.action_navigation_trash_to_detailFragment, bundle)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}