package com.antoinetrouve.bookstore.repository

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.antoinetrouve.bookstore.App
import com.antoinetrouve.bookstore.Book
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import timber.log.Timber

class InsertBookWorker(context : Context, params: WorkerParameters) : Worker(context, params) {

    @UnstableDefault // cause by kotlin serialization experimental
    override fun doWork(): Result {
        Timber.i("Deleting book in processing...")

        // Get data
        val jsonData = inputData.getString("book")

        return try {
            jsonData?.let {
                // Deserialize book
                val book = Json.parse(Book.serializer(), it)

                // Insert book
                App.db.bookDao().insertBook(book)
            }
            Result.success()

        } catch (throwable: Throwable) {
            Timber.e(throwable, "Fail to insert book $jsonData")
            Result.failure()
        }
    }
}
