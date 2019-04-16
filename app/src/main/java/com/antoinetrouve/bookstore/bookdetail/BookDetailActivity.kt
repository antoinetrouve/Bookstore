package com.antoinetrouve.bookstore.bookdetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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

    private lateinit var viewModel: BookDetailViewModel

    companion object {
        const val EXTRA_BOOK_ID = "bookId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        setSupportActionBar(toolbar)

        val bookId = intent.getIntExtra(EXTRA_BOOK_ID, 1)
        Timber.d("BookModel id = $bookId")

        // Pass book id when attach viewModel to activity to keep separate view and model
        val factory = BookDetailViewModelFactory(bookId)
        viewModel = ViewModelProviders.of(this, factory).get(BookDetailViewModel::class.java)

        // Observe book change
        viewModel.book.observe(this, Observer { book ->
            // On Change update book
            updateBook(book!!)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.action_item_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_book -> {
                // Remove book observers to avoid nullPointerException when delete a book
                if (viewModel.book.hasObservers()) viewModel.book.removeObservers(this)

                // Delete the book
                viewModel.onDeleteAction()

                // Comme back to BookListActivity
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
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
