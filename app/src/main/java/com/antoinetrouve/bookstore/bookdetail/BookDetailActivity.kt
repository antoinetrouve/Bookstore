package com.antoinetrouve.bookstore.bookdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.antoinetrouve.bookstore.Book
import com.antoinetrouve.bookstore.R
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.content_book_detail.*
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
        val viewModel = ViewModelProviders.of(this, factory).get(BookDetailViewModel::class.java)
        viewModel.book.observe(this, Observer { book ->
            updateBook(book!!)
        })
    }

    private fun updateBook(book: Book) {
        Picasso.get()
            .load(book.pictureUrl)
            .placeholder(R.drawable.ic_placehoder_image)
            .into(bookCover)

        bookTitle.text = book.title
        bookAuthor.text = book.author
        bookSummary.text = book.summary
    }
}
