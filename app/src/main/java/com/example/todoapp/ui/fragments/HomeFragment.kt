package com.example.todoapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.db.NoteDatabase
import com.example.todoapp.data.entity.Note
import com.example.todoapp.data.repository.NoteRepository
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.ui.adapters.NoteRecyclerView
import com.example.todoapp.ui.viewmodel.NoteViewModel
import com.example.todoapp.ui.viewmodel.NoteViewModelFactory
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val noteDatabase = NoteDatabase.getDatabase(requireActivity())
        val noteRepository = NoteRepository(noteDatabase)
        val viewModelFactory = NoteViewModelFactory(noteRepository)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        noteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        val adapter = NoteRecyclerView()
        noteViewModel.getAllNotes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(activity)
        binding.recyclerview.adapter = adapter
        binding.addNoteFab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_saveNoteFragment)
        }
        return binding.root
    }
}
