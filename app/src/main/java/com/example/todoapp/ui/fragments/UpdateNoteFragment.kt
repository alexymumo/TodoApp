package com.example.todoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.data.db.NoteDatabase
import com.example.todoapp.data.repository.NoteRepository
import com.example.todoapp.databinding.FragmentUpdateNoteBinding
import com.example.todoapp.ui.viewmodel.NoteViewModel
import com.example.todoapp.ui.viewmodel.NoteViewModelFactory

class UpdateNoteFragment : Fragment() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: FragmentUpdateNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val noteDatabase = NoteDatabase.getDatabase(requireActivity())
        val noteRepository = NoteRepository(noteDatabase)
        val viewModelFactory = NoteViewModelFactory(noteRepository)
        binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        noteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        binding.updateNote.setOnClickListener {
            val title = binding.title.text.toString()
            val description = binding.description.text.toString()

            when {
                title.isEmpty() -> {
                    binding.title.error = "Cannot be empty"
                }
                description.isEmpty() -> {
                    binding.description.error = "Cannot be empty"
                }
                else -> {
                    //update note using id
                }
            }
        }
        return binding.root
    }
}