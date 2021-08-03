package com.example.hiltpoc

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Scopes determine whether a class can be injected into another class.
 * See image: https://i.imgur.com/I2v2qtQ.png
 * The order is descendent, meaning:
 * the Application class, which is the only SingletonComponent, cannot be injected into
 * any other components, but it can have any other component injected into it.
 * A class with the @ViewModelScoped annotation can have a @ActivityScoped anotated class injected
 * into it, but it cannot have an @ActivityRetainedScoped anotated class injected into it.
 */

@Singleton
class SingletonClass @Inject constructor() {
}

@ActivityScoped
@AndroidEntryPoint
class ScopeActivity : AppCompatActivity() {

//    This yields a build error
//    @Inject
//    lateinit var fragmentClass: FragmentClass

    @Inject
    lateinit var singletonClass: SingletonClass
}

@FragmentScoped
class FragmentClass @Inject constructor() {
}