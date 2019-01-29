package com.antoinetrouve.bookstore

import android.app.Application
import androidx.room.Room
import com.antoinetrouve.bookstore.database.AppDatabase
import com.antoinetrouve.bookstore.database.DATABASE_NAME
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        // default configuration for debug
        Timber.plant(Timber.DebugTree())
        db = Room.databaseBuilder(this,
            AppDatabase::class.java, DATABASE_NAME)
            .build()
    }
}