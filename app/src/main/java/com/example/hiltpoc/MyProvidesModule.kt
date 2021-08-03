package com.example.hiltpoc

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * The @Module annotation tells Hilt how to provide instances of certain types. Modules must also
 * be annotated with @InstallIn, to tell Hilt which component the module will be installed in.
 *
 * Since we want our Module to be available in MainActivity, which is @ActivityScoped, we will
 * install it in the ActivityComponent.
 */
@Module
@InstallIn(ActivityComponent::class)
class MyProvidesModule {

    /**
     * Interfaces cannot have constructor injections.
     * The @Provides annotation provides for Hilt an implementation for when an interface is needed.
     */
    // Clashes with the binding method from MyBindsModule
    @Provides
    fun provideInterfaceImplementation(): MyInterface {
        return MyInterfaceImpl()
    }

    /**
     * Unlike @Binds, the @Provides annotation can also be used to resolve dependency issues with
     * third party libraries.
     */
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}