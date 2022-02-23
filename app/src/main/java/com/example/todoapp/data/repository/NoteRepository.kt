package com.example.todoapp.data.repository

import com.example.todoapp.data.dao.NoteDao
import com.example.todoapp.data.entity.Note

class NoteRepository(private val noteDao: NoteDao) {

    fun fetchAllNotes() = noteDao.fetchAllNotes()

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
    suspend fun saveNote(note: Note){
        noteDao.saveNote(note)
    }
}