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
import com.example.todoapp.databinding.FragmentUpdateNoteBinding
import com.example.todoapp.ui.viewmodel.NoteViewModel
import com.example.todoapp.ui.viewmodel.NoteViewModelFactory
import com.shashank.sony.fancytoastlib.FancyToast


class UpdateNoteFragment : Fragment() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: FragmentUpdateNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var id:Int = -1
        val noteDatabase = NoteDatabase.getDatabase(requireActivity())
        val noteRepository = NoteRepository(noteDatabase)
        val viewModelFactory = NoteViewModelFactory(noteRepository)

        binding = FragmentUpdateNoteBinding
            .inflate(
                inflater,
                container,
                false
            )

        noteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        arguments.let {
            val args = UpdateNoteFragmentArgs.fromBundle(it!!)
            noteViewModel.getAllNotes.observe(viewLifecycleOwner, {
                binding.title.setText(args.title)
                binding.description.setText(args.description)
                id = args.id
            })
        }
        /*delete code*/
        binding.deleteFab.setOnClickListener {
            val title = binding.title.text.toString()
            val description = binding.description.text.toString()

            when{
                title.isEmpty() -> {
                    binding.title.error = "Cannot delete empty field"
                }
                description.isEmpty() -> {
                    binding.description.error = "Cannot delete empty field"
                }
                else -> {
                    //delete note with id
                    noteViewModel.deleteNote(Note(id, title, description))
                    FancyToast.makeText(
                        activity,
                        "Note Deleted",
                        FancyToast.LENGTH_LONG,
                        FancyToast.SUCCESS,
                        true
                    ).show()
                }
              }
        }

        /*update code*/
        binding.updateFab.setOnClickListener {
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

                    noteViewModel.updateNote(Note(id, title, description))
                    FancyToast.makeText(
                        activity,
                        "Note Updated",
                        FancyToast.LENGTH_LONG,FancyToast.SUCCESS,
                        true
                    ).show()
                    findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
                }
            }
        }
        return binding.root
    }
}