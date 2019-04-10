package com.antoinetrouve.bookstore.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antoinetrouve.bookstore.Book

const val DATABASE_NAME = "book_store"

/**
 * Note : Export Database schema directory is provided in gradle file
 * See : https://stackoverflow.com/questions/44322178/room-schema-export-directory-is-not-provided-to-the-annotation-processor-so-we
 */
@Database(entities = [Book::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}