package com.antoinetrouve.bookstore.bookdetail

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders
import com.antoinetrouve.bookstore.Book
import com.antoinetrouve.bookstore.R

import kotlinx.android.synthetic.main.activity_book_detail.*
import timber.log.Timber

class BookDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_BOOK_ID = "bookId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        setSupportActionBar(toolbar)

        val bookId = intent.getIntExtra(EXTRA_BOOK_ID, 1)
        Timber.d("Book id = $bookId")

        // Pass book id when attach viewModel to activity without call constructor directly to keep
        // separate view and model
        val factory = BookDetailViewModelFactory(bookId)
        ViewModelProviders.of(this, factory).get(BookDetailViewModel::class.java)

    }

}
