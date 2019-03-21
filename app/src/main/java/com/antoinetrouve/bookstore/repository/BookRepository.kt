package com.antoinetrouve.bookstore.repository

import androidx.work.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BookRepository {

    // Creation constraint
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    fun syncBookNow() {
        Timber.i("Synchronization books now")
        val work = OneTimeWorkRequestBuilder<SyncRepositoryWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance()
            .beginUniqueWork("syncBookNow", ExistingWorkPolicy.KEEP, work)
            .enqueue()
    }

    fun scheduleBooksSync() {
        Timber.i("Synchronization books every 12 hours")
        val work = PeriodicWorkRequestBuilder<SyncRepositoryWorker>(12, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance()
            .enqueueUniquePeriodicWork("syncBooksScheduled", ExistingPeriodicWorkPolicy.KEEP, work)
    }
}