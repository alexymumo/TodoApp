package com.example.todoapp.di

import com.example.todoapp.data.dao.NoteDao
import com.example.todoapp.data.db.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao
    }

}

