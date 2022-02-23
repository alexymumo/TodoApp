package com.example.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoapp.data.entity.Note

@Dao
interface NoteDao {
    @Query("SELECT *FROM note_table ORDER BY title ASC")
    fun fetchAllNotes(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteNote(note: Note)
}