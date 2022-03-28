package com.example.todoapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.todoapp.data.dao.NoteDao
import com.example.todoapp.data.entity.Note
import org.junit.After
import org.junit.Assert
import org.junit.Assert.* // ktlint-disable no-wildcard-imports
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private var noteDao: NoteDao? = null

    @Before
    fun setup() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.todoapp", appContext.packageName)
    }

    @Test
    suspend fun saveNoteTest() {
        val note = Note(1, "Code", "Machine learning")
        noteDao?.saveNote(note)
        Assert.assertEquals(note.description, note.id, note.title)
    }
}
