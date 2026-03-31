# FinTrack

FinTrack is an Android application for tracking personal finances, built with Jetpack Compose, Hilt, Room, and Firebase Firestore.

## Getting Started

To get this project running locally, you need to set up Firebase:

1.  Create a new project in the [Firebase Console](https://console.firebase.google.com/).
2.  Add an Android app with the package name `com.sri_sourish.fintrack`.
3.  Download the `google-services.json` file.
4.  Place the `google-services.json` file in the `app/` directory of this project.
5.  Open `app/build.gradle.kts` and uncomment the following lines:
    ```kotlin
    // alias(libs.plugins.google.services)
    // id("com.google.gms.google-services")
    ```
6.  Sync the project with Gradle files.

## Tech Stack

- **Jetpack Compose**: UI Toolkit.
- **Hilt**: Dependency Injection.
- **Room**: Local Database.
- **Firebase Firestore**: Remote Synchronization.
- **Vico**: Charts and Graphs.
