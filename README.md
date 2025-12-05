# Dishnara: A Kotlin Multiplatform Showcase

This project serves as a comprehensive showcase of a Kotlin Multiplatform (KMP) application built
with modern, production-ready technologies. It demonstrates how to build a functional,
cross-platform application for Android, iOS, and Desktop from a single, shared codebase.

## Project Purpose

The primary goal of Dishnara is to provide a practical example and a starting point for developers
interested in Kotlin Multiplatform. It illustrates best practices for:

- **Code Sharing:** Maximizing shared code across business logic, data layers, and even the UI.
- **Architecture:** Implementing a clean, scalable, and maintainable architecture.
- **Library Integration:** Integrating popular and powerful libraries within a KMP environment.

## Application Overview

Dishnara is a simple recipe-browsing application. It fetches data from a remote API, displays it in
a list, and allows users to view recipe details. It's built to be simple enough to understand
quickly but complex enough to demonstrate real-world application structure.

## Key Technologies & Concepts Demonstrated

This project showcases the integration and usage of the following key technologies:

- **[Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform-mobile-getting-started.html):**
  The core technology for sharing code across platforms.
- **[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform):** For building a
  shared, declarative UI for Android, iOS, and Desktop.
- **Clean Architecture:** The project is structured into UI, ViewModel, Repository, and Data Source
  layers.
- **[Ktor](https://ktor.io/):** As the client for making network requests to a remote API.
- **[Room](https://developer.android.com/training/data-storage/room):** For local database and
  offline caching, made multiplatform-compatible.
- **[Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization):** For parsing JSON
  data from the network.
- **[Coil 3](https://coil-kt.github.io/coil/):** A multiplatform image loading library.
- **ViewModel:** Using `lifecycle-viewmodel-compose` for state management in the UI.
- **[Koin](https://insert-koin.io/):** A pragmatic lightweight dependency injection framework for
  Kotlin & Kotlin Multiplatform.

## Features Implemented

- **Shared UI & Business Logic:** One codebase for the user interface and application logic.
- **Recipe List:** Fetches and displays a list of recipes.
- **Recipe Details:** Shows detailed information for a selected recipe.
- **Offline Caching:** Saves data locally for offline browsing.
- **Pull-to-Refresh:** Updates the recipe list with the latest data.
- **Platform Support:** Android, iOS, and Desktop (Windows, macOS, Linux).

## How to Build

To build and run this project, you need Android Studio with the Kotlin Multiplatform Mobile plugin.

1. Clone the repository.
2. Open the project in Android Studio.
3. Let Gradle sync and download dependencies.
4. Select the desired run configuration (e.g., `composeApp`, `iosApp`, or `desktopApp`) and run.

---

Feel free to explore the code, use it as a reference for your own projects, and contribute if you
have ideas for improvement!
