package com.antoinetrouve.bookstore.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.antoinetrouve.bookstore.App
import com.antoinetrouve.bookstore.Book

class BooksListViewModel : ViewModel() {
    // Todo pass repository into constructor parameter
    val books: LiveData<List<Book>> = App.db.bookDao().getAllBooks()

    fun refreshBooks() {
        App.repository.syncBookNow()
    }
}