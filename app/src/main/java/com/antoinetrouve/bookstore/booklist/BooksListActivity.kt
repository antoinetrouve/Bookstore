package com.antoinetrouve.bookstore.booklist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoinetrouve.bookstore.Book
import com.antoinetrouve.bookstore.R
import com.antoinetrouve.bookstore.bookdetail.BookDetailActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_books_list.*
import kotlinx.serialization.UnstableDefault
import timber.log.Timber

class BooksListActivity : AppCompatActivity(), BooksListAdapter.BooksListAdapterListener {

    private lateinit var viewModel: BooksListViewModel
    private lateinit var booksAdapter: BooksListAdapter
    private lateinit var books: MutableList<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_list)
        books = mutableListOf()
        booksAdapter = BooksListAdapter(books, this)

        // Init recycler view with view root and adapter
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@BooksListActivity)
            adapter = booksAdapter
        }

        // Init swipe refresh action
        swipeRefresh.setOnRefreshListener { viewModel.refreshBooks() }

        // Init swipe delete action
        ItemTouchHelper(SwipeToDeleteHandler(this, booksAdapter)).attachToRecyclerView(recyclerView)

        // create viewModel instance
        viewModel = ViewModelProviders.of(this).get(BooksListViewModel::class.java)

        // subscribe to change event on books list (thanks to LiveData)
        viewModel.books.observe(this, Observer { newBooks ->
            updateBooks(newBooks!!)
        })
    }


    /**
     * Start detail activity for a selected book
     */
    override fun onBookSelected(book: Book) {
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra(BookDetailActivity.EXTRA_BOOK_ID, book.id)
        startActivity(intent)
    }


    /**
     * Delete the book swiped
     */
    @UnstableDefault // cause by experimental kotlin serialization extension
    override fun onBookDeleted(book: Book) {
        // Delete the book, (no need to notify adapter thanks to liveData books observe)
        viewModel.deleteBook(book)
        showUndoDeleteAction(book)
    }

    /**
     * Undo the deleted action to prevent user error
     */
    @UnstableDefault // cause by experimental kotlin serialization extension
    private fun showUndoDeleteAction(book: Book) {
        val snackbar = Snackbar
            .make(rootView, R.string.snack_bar_undo_action_text, Snackbar.LENGTH_LONG)
            .setAction(R.string.snack_bar_undo_action) { viewModel.insertBook(book) }

        // apply snackbar extension
        snackbar.config()

        // show
        snackbar.show()
    }

    /**
     * Update the book list
     */
    private fun updateBooks(newBooks: List<Book>) {
        Timber.d("List of books $newBooks")
        books.clear()
        books.addAll(newBooks)
        booksAdapter.notifyDataSetChanged()
        swipeRefresh.isRefreshing = false
    }

    private fun Snackbar.config() {
        val params = view.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(12, 12, 12, 24)
        view.layoutParams = params
        view.background = ContextCompat.getDrawable(context, R.drawable.snackbar)

        ViewCompat.setElevation(view, 6f)
    }
}
