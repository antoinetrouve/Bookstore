package com.antoinetrouve.bookstore.bookdetail

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
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

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
