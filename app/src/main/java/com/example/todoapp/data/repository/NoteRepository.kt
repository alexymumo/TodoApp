package com.example.todoapp.data.repository

import com.example.todoapp.data.db.NoteDatabase
import com.example.todoapp.data.entity.Note

class NoteRepository(private val noteDatabase: NoteDatabase) {

    fun fetchAllNotes() = noteDatabase.noteDao.fetchAllNotes()

    suspend fun deleteNote(note: Note) {
        noteDatabase.noteDao.deleteNote(note)
    }
    suspend fun updateNote(note: Note) {
        noteDatabase.noteDao.updateNote(note)
    }
    suspend fun saveNote(note: Note) {
        noteDatabase.noteDao.saveNote(note)
    }
}
