# Bookstore

![Version](https://img.shields.io/badge/Version-1.0-brightgreen.svg)
![System](https://img.shields.io/badge/Version-4.1--8.1-green.svg?logo=Android&longCache=true&style=flat)
![Kotlin](https://img.shields.io/badge/Kotlin-1.3-blue.svg?logo=Kotlin&longCache=true&style=flat)
![Room](https://img.shields.io/badge/Room-2.1.0-9cf.svg)
![Lifecycle](https://img.shields.io/badge/Lifecycle-2.1.0-9cf.svg)
![WorkManager](https://img.shields.io/badge/WorkManager-1.0.0-9cf.svg)
![Picasso](https://img.shields.io/badge/Picasso-2.71828-%23B94948.svg)
![Timber](https://img.shields.io/badge/Timber-4.7.1-yellow.svg)

## Functionality

### What can I do with the application ?
- Show a list of book and get a detail when selected one

### On which device can I use the application ?
- You can use it on your phone only. Tablet wil not supported.
- The application is running on minimum Android system Jelly Bean 4.1

## Technical stack
- **Current version** : 1.0.0
- **Development language** : [Koltin](https://kotlinlang.org/) release 1.3
- **Normal permission** : Internet access 

### Android system available
- **Minimum** : Jelly Bean 4.1 (API Level 16)
- **Recommended** : Orea 8.1 (API Level 27)

### The libraries
- The application is using the Android Architecture component, *a collection of libraries that help you design robust, testable, and maintainable apps.* See https://developer.android.com/topic/libraries/architecture
- MVVM pattern. See https://developer.android.com/jetpack/docs/guide

| Library | Description | For more detail |
| ----------- | ----------- | ----------- |
| **Room v. 2.1.0** | persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite. | https://developer.android.com/topic/libraries/architecture/room
| **View Model - Live Data v. 2.1.0** | designed to store and manage UI-related data in a lifecycle conscious way. | https://developer.android.com/topic/libraries/architecture/viewmodel
| **Work manager v. 1.0.0** | The WorkManager API makes it easy to schedule deferrable, asynchronous tasks that are expected to run even if the app exits or device restarts. | https://developer.android.com/topic/libraries/architecture/workmanager
| **Picasso v. 2.71828** | An image downloading and caching library for Android | http://square.github.io/picasso/

## To come up ?
- Improve local database management to offer the possibility to the user to delete a book
- Improve the local database to load more books with an open API
- Improve UI using fragment

## Author
<a href="http://www.linkedin.com/in/antoinetrouve-devmobile" target="_blank">**Antoine Trouvé**</a>,
Android and Symfony developper
