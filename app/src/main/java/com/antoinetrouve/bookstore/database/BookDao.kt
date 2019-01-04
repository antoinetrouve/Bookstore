package com.antoinetrouve.bookstore.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.antoinetrouve.bookstore.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAllBooks() : LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE id = :id")
    fun getBookById(id: Int): LiveData<Book>

    @Insert
    fun insertBooks(books: List<Book>)
}