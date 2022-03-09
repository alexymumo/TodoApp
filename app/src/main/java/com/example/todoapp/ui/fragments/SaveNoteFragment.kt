package com.example.todoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.data.db.NoteDatabase
import com.example.todoapp.data.entity.Note
import com.example.todoapp.data.repository.NoteRepository
import com.example.todoapp.databinding.FragmentSaveNoteBinding
import com.example.todoapp.ui.viewmodel.NoteViewModel
import com.example.todoapp.ui.viewmodel.NoteViewModelFactory
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveNoteFragment : Fragment() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: FragmentSaveNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val noteDatabase = NoteDatabase.getDatabase(requireActivity())
        val noteRepository = NoteRepository(noteDatabase)
        val viewModelFactory = NoteViewModelFactory(noteRepository)
        binding = FragmentSaveNoteBinding.inflate(inflater, container, false)
        noteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        binding.saveNote.setOnClickListener {
            val title = binding.title.text.toString()
            val description = binding.description.text.toString()

            when {
                title.isEmpty() -> {
                    binding.title.error = "Cannot be empty"
                }
                description.isEmpty() -> {
                    binding.description.error = "Cannot be empty"
                }else -> {
                    noteViewModel.saveNote(Note(0, title, description))
                    FancyToast.makeText(
                        activity,
                        "Note Saved",
                        FancyToast.LENGTH_LONG,
                        FancyToast.SUCCESS,
                        true
                    ).show()
                    findNavController().navigate(R.id.action_saveNoteFragment_to_homeFragment)
                }
            }
        }
        return binding.root
    }
}