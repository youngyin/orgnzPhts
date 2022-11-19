package com.orgnzphts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.orgnzphts.databinding.FragmentTrashBinding
import com.orgnzphts.viewmodel.TrashViewModel

class TrashFragment : Fragment() {

    private var _binding: FragmentTrashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val trashViewModel = ViewModelProvider(this).get(TrashViewModel::class.java)
        _binding = FragmentTrashBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}