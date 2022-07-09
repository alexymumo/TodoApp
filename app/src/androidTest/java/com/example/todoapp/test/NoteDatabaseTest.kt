package com.example.todoapp.test

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.todoapp.data.dao.NoteDao
import com.example.todoapp.data.db.NoteDatabase
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteDatabaseTest : TestCase() {
    private lateinit var db: NoteDatabase
    private lateinit var noteDao: NoteDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java).build()
        noteDao = db.noteDao
    }
    @After
    fun closeDb() {
        db.close()
    }
}
