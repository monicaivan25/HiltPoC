package com.example.hiltpoc

import dagger.Binds
import dagger.Module
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
abstract class MyBindsModule {

    /**
     * Interfaces cannot have constructor injections.
     * The @Binds annotation over an abstract method, tells Hilt which implementation to use.
     */
    // Clashes with the provide method from MyBindsModule
    @Binds
    abstract fun bindInterfaceImplementation(interfaceImpl: MyInterfaceImpl): MyInterface
}