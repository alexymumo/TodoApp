package com.example.todoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.databinding.FragmentSaveNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveNoteFragment : Fragment() {
    private lateinit var binding: FragmentSaveNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
}