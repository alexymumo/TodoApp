package com.example.todoapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.* // ktlint-disable no-wildcard-imports
import com.example.todoapp.data.entity.Note

@Dao
interface NoteDao {
    @Query("SELECT *FROM note_table ORDER BY title ASC")
    fun fetchAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)
}
