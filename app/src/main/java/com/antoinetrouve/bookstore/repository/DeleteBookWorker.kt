package com.antoinetrouve.bookstore.repository

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.antoinetrouve.bookstore.App
import timber.log.Timber

class DeleteBookWorker(context : Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        Timber.i("Deleting book in processing...")
        val bookId = inputData.getLong("BookId", 0)

        return try {
            App.db.bookDao().deleteBook(bookId)
            Result.success()

        } catch (throwable: Throwable) {
            Timber.e(throwable, "Fail to delete book id $bookId")
            Result.failure()
        }
    }
}
