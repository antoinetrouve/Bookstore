package com.antoinetrouve.bookstore.booklist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoinetrouve.bookstore.Book
import com.antoinetrouve.bookstore.R
import com.antoinetrouve.bookstore.bookdetail.BookDetailActivity
import kotlinx.android.synthetic.main.activity_books_list.*
import timber.log.Timber

class BooksListActivity : AppCompatActivity(), BooksListAdapater.BooksListAdapterListener {
    private lateinit var viewModel: BooksListViewModel
    private lateinit var booksAdapter: BooksListAdapater
    private lateinit var books: MutableList<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_list)

        books = mutableListOf()
        booksAdapter = BooksListAdapater(books, this)

        // Ini recycler view with view root and adapter
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@BooksListActivity)
            adapter = booksAdapter
        }

        swipeRefresh.setOnRefreshListener { viewModel.refreshBooks() }

        // create viewModel instance
        viewModel = ViewModelProviders.of(this).get(BooksListViewModel::class.java)
        viewModel.books.observe(this, Observer { newBooks ->
            updateBooks(newBooks!!)
        })
    }

    private fun updateBooks(newBooks: List<Book>) {
        Timber.d("List of books $newBooks")
        books.clear()
        books.addAll(newBooks)
        booksAdapter.notifyDataSetChanged()
        swipeRefresh.isRefreshing = false
    }

    override fun onBookSelected(book: Book) {
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra(BookDetailActivity.EXTRA_BOOK_ID, book.id)
        startActivity(intent)
    }
}
