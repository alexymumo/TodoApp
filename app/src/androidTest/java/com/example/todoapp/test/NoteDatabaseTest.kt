package com.example.todoapp.test

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.todoapp.data.dao.NoteDao
import com.example.todoapp.data.db.NoteDatabase
import com.example.todoapp.data.entity.Note
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteDatabaseTest : TestCase() {

    private lateinit var noteDao: NoteDao
    private lateinit var noteDatabase: NoteDatabase

    @Before
    public override fun setUp() {
        val ctx = ApplicationProvider.getApplicationContext<Context>()
        noteDatabase = Room.inMemoryDatabaseBuilder(
            ctx, NoteDatabase::class.java
        ).build()
        noteDao = noteDatabase.noteDao
    }

    @After
    fun closeDb() {
        noteDatabase.close()
    }

    @Test
    fun writeAndRead() = runBlocking {
        val note = Note(1, "Linux", "Learn processes")
        noteDao.saveNote(note)
        val notes = noteDao.fetchAllNotes()
        // assertThat()
    }
}
