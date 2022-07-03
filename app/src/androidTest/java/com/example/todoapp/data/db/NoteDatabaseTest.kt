package com.example.todoapp.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.todoapp.data.dao.NoteDao
import com.example.todoapp.data.entity.Note
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class NoteDatabaseTest : TestCase() {

    private lateinit var notedb: NoteDatabase
    private lateinit var notedao: NoteDao

    // Executes before each test
    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        notedb = Room.inMemoryDatabaseBuilder(
            context, NoteDatabase::class.java
        ).build()
        notedao = notedb.noteDao
    }
    @Test
    fun testSave() = runBlocking {
        val note = Note(1, "alex", "learning", 2)
        notedao.saveNote(note)
        val notes = notedao.fetchAllNotes()
        assertThat(notes.value?.contains(note)).isTrue()
    }

    // Executes after each test
    @After
    @Throws(IOException::class)
    fun closeDb() {
        notedb.close()
    }
}
/*
* */
