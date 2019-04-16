package com.antoinetrouve.bookstore.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.antoinetrouve.bookstore.App
import com.antoinetrouve.bookstore.Book
import kotlinx.serialization.UnstableDefault

class BooksListViewModel : ViewModel() {
    // Todo pass repository into constructor parameter
    val books: LiveData<List<Book>> = App.db.bookDao().getAllBooks()

    fun refreshBooks() {
        App.repository.syncBookNow()
    }

    fun deleteBook(book: Book) {
        App.repository.deleteBook(book.id)
    }

    @UnstableDefault // cause by kotlin serialization experimental
    fun insertBook(book: Book) {
        App.repository.insertBook(book)
    }
}