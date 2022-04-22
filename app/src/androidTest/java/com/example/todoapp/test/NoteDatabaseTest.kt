package com.example.todoapp.test

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.todoapp.data.dao.NoteDao
import com.example.todoapp.data.db.NoteDatabase
import com.example.todoapp.data.entity.Note
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class NoteDatabaseTest : TestCase() {
    private lateinit var noteDao: NoteDao
    private lateinit var noteDatabase: NoteDatabase

    @Before
    override fun setUp() {
        val ctx = ApplicationProvider.getApplicationContext<Context>()
        noteDatabase = Room.inMemoryDatabaseBuilder(
            ctx, NoteDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        noteDao = noteDatabase.noteDao
    }

    // execute after every test case
    @After
    fun closeDb() {
        noteDatabase.close()
    }

    // test case to save a note
    @Test
    fun saveNoteTestCase() = runBlocking {
        val note = Note(1, "Linux", "Learn processes")
        noteDao.saveNote(note)
        val notes = noteDao.fetchAllNotes()
        assertThat(notes)
    }

    @Test
    fun deleteNoteTest() = runBlocking {
        val note = Note(1, "Linux", "Learn processes")
        noteDao.saveNote(note)
        noteDao.deleteNote(note)
        val notes = noteDao.fetchAllNotes()
        assertThat(notes)
    }
    @Test
    fun updateNoteTest() = runBlocking{
        val note = Note(1, "Linux", "Learn processes")
        noteDao.saveNote(note)
        val newNote = note.copy(title = "Kotlin")
        noteDao.updateNote(newNote)
        val notes = noteDao.fetchAllNotes()
        assertThat(notes)
    }
}
