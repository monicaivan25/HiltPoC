package com.example.hiltpoc

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @HiltAndroidApp generates a base class serving as an app-level dependency container.
 * This component is attached to the Application object’s lifecycle, and provides dependencies to it.
 *
 * The scope is @Singleton.
 */
@HiltAndroidApp
class MyApplication : Application() {
}