package com.antoinetrouve.bookstore

import android.app.Application
import androidx.room.Room
import com.antoinetrouve.bookstore.database.AppDatabase
import com.antoinetrouve.bookstore.database.DATABASE_NAME
import com.antoinetrouve.bookstore.repository.BookRepository
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var db: AppDatabase
        lateinit var repository: BookRepository
    }

    override fun onCreate() {
        super.onCreate()

        // default configuration for debug
        Timber.plant(Timber.DebugTree())
        db = Room.databaseBuilder(this,
            AppDatabase::class.java, DATABASE_NAME)
            .build()

        repository = BookRepository()
        repository.scheduleBooksSync()
    }
}