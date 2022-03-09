package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.entity.Note
import com.example.todoapp.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteRepository: NoteRepository
    ): ViewModel() {

    val getAllNotes: LiveData<List<Note>> = noteRepository.fetchAllNotes()

    fun saveNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.saveNote(note)
    }
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.deleteNote(note)
    }
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.updateNote(note)
    }
}