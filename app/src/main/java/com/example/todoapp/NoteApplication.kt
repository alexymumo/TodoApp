package com.example.todoapp

import android.app.Application
import timber.log.Timber

class NoteApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}